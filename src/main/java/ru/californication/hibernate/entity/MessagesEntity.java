package ru.californication.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "messages", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })

public class MessagesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private String sender;
	
	@Column(nullable = false)
	private String receiver;
	
	@Column(nullable = false)
	private String sendDate;
	
	@Column(nullable = false)
	@Type(type="text")
	private String text;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate() {
		this.sendDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
