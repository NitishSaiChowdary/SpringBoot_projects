package com.dl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chatcontroller {

	
	private final ChatClient chatClient;

	public chatcontroller(ChatClient.Builder builder) {
		super();
		this.chatClient = builder
				//.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.defaultAdvisors(new PromptChatMemoryAdvisor(new InMemoryChatMemory()))
				
				.build();
	}
	@GetMapping
	public String chat(@RequestParam String q) {
		return chatClient
				.prompt()
				.user(q)
				.call()
				.content();
		
	}
}
