package com.websocket.chyatt.Models;


public class Message {
    private String content;
    private String sender;
    private char type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Message(String content, String sender, char type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    public Message(String sender, char type) {
        this.sender = sender;
        this.type = type;
    }

    public Message() {
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}
