package com.cxyxl.controller;

import com.cxyxl.Token.UserLoginToken;
import com.cxyxl.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {

    @Value("${filePath}")
    String filePath;


    @PostMapping("/post/img/upload")
    @UserLoginToken
    public Response upload(MultipartFile image) {
        Response response = new Response();
        //获取文件名
        String fileName = image.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID() + suffixName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        String filePath = this.filePath;
        try {
            //将图片保存到static文件夹里
            String pathname = filePath + fileName;
            image.transferTo(new File(pathname));
            //返回提示信息
            response.setCode(200).setSuccess(true).setData(fileName).setMsg("save success");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(500).setSuccess(true).setData(null).setMsg("save failed");
        }
        return response;
    }

    @PostMapping("/post/img/delete")
    @UserLoginToken
    public Response delete(@RequestBody Map<String, String> map) {
        String fileName = map.get("imageName");
        String filePath = this.filePath + fileName;
        File file = new File(filePath);
        boolean flag = false;
        // 判断目录或文件是否存在
        if (file.exists()) {  // 不存在返回 false
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                flag = file.delete();
            }
        }
        if (flag) {
            return Response.builder().code(200).success(true).build();
        }
        return Response.builder().code(500).build();
    }

}

