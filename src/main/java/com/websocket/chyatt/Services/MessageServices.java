package com.websocket.chyatt.Services;

import com.websocket.chyatt.DAO.MessageDAO;
import com.websocket.chyatt.Models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageServices {
    private MessageDAO messagedao;

    public MessageServices(MessageDAO messagedao) {
        this.messagedao = messagedao;
    }

    //saving message in Database
    public void saveMessage(Message message){
        messagedao.save(message);
        System.out.println("Message saved");
        System.out.println(message);
    }
}
