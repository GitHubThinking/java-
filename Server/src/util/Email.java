package util;

import java.util.Date;

public class Email implements java.io.Serializable {
	private static final long serialVersionUID = 2639876051429484767L;
	private int sender;
	private int receiver;
	private String title;
	private String body;
	private Date sendingTime;

	public Email() {
		// setActionDate(new Date());
	}

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public Date getSendingTime() {
		return sendingTime;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setSendingTime(Date sendingTime) {
		this.sendingTime = sendingTime;
	}

}
