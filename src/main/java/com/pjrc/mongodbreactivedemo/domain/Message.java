package com.pjrc.mongodbreactivedemo.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "message")
public class Message {
	
	@Id
	private String id = UUID.randomUUID().toString().substring(0, 10);
	private String mailFrom;
//	private String mailTo;
//	private Date sendedDate = new Date();
//	private String subject;
//	private String body;
	private String threadId;
}
