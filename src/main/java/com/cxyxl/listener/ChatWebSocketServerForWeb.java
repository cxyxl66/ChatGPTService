package com.cxyxl.listener;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cxyxl.bean.Role;
import com.cxyxl.service.RoleService;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Component
@ServerEndpoint("/chatWebSocketForWeb/{uid}")
@Slf4j
public class ChatWebSocketServerForWeb {
    
    private static int onlineCount = 0;

    private static ConcurrentHashMap<String, ChatWebSocketServerForWeb> chatWebSocketMap = new ConcurrentHashMap<>();

    private static CopyOnWriteArraySet<ChatWebSocketServerForWeb> webSocketSet = new CopyOnWriteArraySet<>();
    
    private final static List<Session> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    private static OpenAiStreamClient openAiStreamClient;

    private static RoleService roleService;
    private Session session;
    
    private String uid = "";

    @Resource
    public void setRoleService(RoleService roleService) {
        ChatWebSocketServerForWeb.roleService = roleService;
    }

    @Resource
    public void setOpenAiStreamClient(OpenAiStreamClient openAiStreamClient) {
        this.openAiStreamClient = openAiStreamClient;
    }

    /**
     * 建立连接
     * @param session 会话
     * @param uid 连接用户名称
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        log.info("websocket open,uid:{}", uid);
        this.session = session;
        this.uid = uid;
        webSocketSet.add(this);
        SESSIONS.add(session);

        if (chatWebSocketMap.containsKey(uid)) {
            chatWebSocketMap.remove(uid);
            chatWebSocketMap.put(uid, this);
        } else {
            chatWebSocketMap.put(uid, this);
            addOnlineCount();
        }
        log.info("websocket onOpen, userId:{}, online count:{}", this.uid, getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        if (chatWebSocketMap.containsKey(uid)) {
            chatWebSocketMap.remove(uid);
            subOnlineCount();
        }
        log.info("websocket onClose, userId:{}, online count:{}", uid, getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("onMessage, userId:{} ", this.uid);
        JSONObject jsonObject = JSONUtil.parseObj(message);
        Integer roleId = jsonObject.getInt("roleId");
        String messageString = jsonObject.getStr("message");
        List<Message> messages = new ArrayList<>();
        messages = JSONUtil.toList(messageString, Message.class);
        //接受参数
        OpenAIWebSocketEventSourceListener eventSourceListener = new OpenAIWebSocketEventSourceListener(this.session);

        ChatCompletion chatCompletion = buildChatCompletion(roleId, messages);
        openAiStreamClient.streamChatCompletion(chatCompletion, eventSourceListener);
    }

    private ChatCompletion buildChatCompletion(int id, List<Message> messages) {
        ChatCompletion chatCompletion;
        Role role = roleService.getRoleById(id);
        if (role != null) {
            Message roleMessage = Message.builder().content(role.getRoleMessage())
                    .role(Message.Role.SYSTEM).build();
            messages.add(0, roleMessage);
            chatCompletion = ChatCompletion.builder()
                    .temperature(role.getTemperature())
                    .model(role.getModel())
                    .maxTokens(role.getMaxTokens())
                    .topP(role.getTopP())
                    .presencePenalty(role.getPresencePenalty())
                    .frequencyPenalty(role.getFrequencyPenalty())
                    .messages(messages)
                    .stream(true)
                    .user(this.uid)
                    .build();
        } else {
            chatCompletion = ChatCompletion.builder()
                    .messages(messages)
                    .stream(true)
                    .user(this.uid)
                    .build();
        }
        return chatCompletion;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("onError, userId:{}, message:{}", this.uid, error.getMessage());
        error.printStackTrace();
    }

    /**
     * 获取当前连接数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 当前连接数加一
     */
    public static synchronized void addOnlineCount() {
        ChatWebSocketServerForWeb.onlineCount++;
    }

    /**
     * 当前连接数减一
     */
    public static synchronized void subOnlineCount() {
        ChatWebSocketServerForWeb.onlineCount--;
    }
}
