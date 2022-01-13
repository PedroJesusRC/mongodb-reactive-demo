package com.pjrc.mongodbreactivedemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjrc.mongodbreactivedemo.domain.Message;
import com.pjrc.mongodbreactivedemo.repository.MessageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Mono<Message> save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public Mono<Message> delete(String id) {
		return messageRepository.findById(id)
				.flatMap(m -> messageRepository.deleteById(m.getId()).thenReturn(m));
	}

	@Override
	public Mono<Message> update(String id, Message message) {
		return messageRepository.findById(id)
				.flatMap(m -> {
					message.setId(id);
					return messageRepository.save(message);
				})
				.switchIfEmpty(Mono.empty());
				
	}

	@Override
	public Flux<Message> findByThreadId(String threadId) {
		return messageRepository.findByThreadId(threadId);
	}

	@Override
	public Flux<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Mono<Message> findById(String id) {
		return messageRepository.findById(id);
	}

}
