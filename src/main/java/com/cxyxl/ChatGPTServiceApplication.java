package com.cxyxl;

import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@SpringBootApplication
@MapperScan("com.cxyxl.dao")
public class ChatGPTServiceApplication {

    @Value("${chatgpt.apiKey}")
    private List<String> apiKey;
    @Value("${chatgpt.apiHost}")
    private String apiHost;

    public static void main(String[] args) {
        SpringApplication.run(ChatGPTServiceApplication.class, args);
    }

    @Bean
    public OpenAiStreamClient openAiStreamClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .build();
        return OpenAiStreamClient
                .builder()
                .apiHost(apiHost)
                .apiKey(apiKey)
                .keyStrategy(new KeyRandomStrategy())
                .okHttpClient(okHttpClient)
                .build();
    }
}
