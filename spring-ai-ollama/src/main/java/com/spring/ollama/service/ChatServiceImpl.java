package com.spring.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder)
    {
        this.chatClient=builder.build();
    }
    @Override
    public String chat(String query) {
        String prompt = "tell me about virat kohli";

        String content = chatClient.prompt().user(prompt).system("As as expert in cricket.").call().content();

        return content;
    }
}
