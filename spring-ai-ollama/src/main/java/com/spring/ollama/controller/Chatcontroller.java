package com.spring.ollama.controller;

import com.spring.ollama.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Chatcontroller {

//    private ChatClient ollamaChatClient;

//    public Chatcontroller(ChatClient.Builder builder){
//        this.chatClient=builder.build();
//    }

//    public Chatcontroller(@Qualifier("ollamaChatClient") ChatClient ollamaChatClient){
//        this.ollamaChatClient=ollamaChatClient;
//    }

    private ChatService chatService;

    public Chatcontroller(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String query)
    {
        return ResponseEntity.ok(chatService.chat(query));

//        String responseContent = this.ollamaChatClient.prompt(query).call().content();
//        return ResponseEntity.ok(responseContent);
    }
}
