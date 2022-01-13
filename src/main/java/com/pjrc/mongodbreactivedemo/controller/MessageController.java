package com.pjrc.mongodbreactivedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pjrc.mongodbreactivedemo.domain.Message;
import com.pjrc.mongodbreactivedemo.service.MessageService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	private Mono<Message> save(@RequestBody Message message){
		return messageService.save(message);
	}
	
	@DeleteMapping("/messages/{id}")
    private Mono<ResponseEntity<Message>> delete(@PathVariable("id") String id) {
        return this.messageService.delete(id)
                .flatMap(message -> Mono.just(ResponseEntity.ok(message)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/messages/{id}")
    private Mono<ResponseEntity<Message>> update(@PathVariable("id") String id, @RequestBody Message message) {
        return this.messageService.update(id, message)
                .flatMap(message1 -> Mono.just(ResponseEntity.ok(message1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping("/messages/{threadId}/bythreadId")
    private Flux<Message> findAllByThreadId(@PathVariable("threadId") String threadId) {
        return this.messageService.findByThreadId(threadId);
    }

    @GetMapping(value = "/messages")
    private Flux<Message> findAll() {
        return this.messageService.findAll();
    }
}
