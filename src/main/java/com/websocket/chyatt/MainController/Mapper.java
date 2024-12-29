package com.websocket.chyatt.MainController;

import com.websocket.chyatt.Models.Message;
import com.websocket.chyatt.Services.MessageServices;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class Mapper {
    private MessageServices messageservices;

    public Mapper(MessageServices messageservices) {
        this.messageservices = messageservices;
    }

    @MessageMapping("/userMessage")
    @SendTo("/topic/main")
    public Message sendMessage(@Payload Message message){
        System.out.println("Username:" +message.getSender());
        System.out.println("type of message: "+ message.getType());
        messageservices.saveMessage(message);
        return message;

    }




}
