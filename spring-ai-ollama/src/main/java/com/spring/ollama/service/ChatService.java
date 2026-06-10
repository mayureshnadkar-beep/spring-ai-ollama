package com.spring.ollama.service;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    String chat(String query);
}
