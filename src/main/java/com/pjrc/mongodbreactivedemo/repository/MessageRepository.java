package com.pjrc.mongodbreactivedemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pjrc.mongodbreactivedemo.domain.Message;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String>{
	
	Flux<Message> findByThreadId(String threadId);
}
