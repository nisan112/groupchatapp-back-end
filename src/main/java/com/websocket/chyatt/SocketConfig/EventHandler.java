package com.websocket.chyatt.SocketConfig;

import com.websocket.chyatt.Models.Message;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

@Component
public class EventHandler {
    private final SimpMessagingTemplate template;

    public EventHandler(SimpMessagingTemplate template) {
        this.template = template;
    }

    @EventListener
    public void onDisconnection(SessionDisconnectEvent diss) {
        System.out.println("On disconnect event is triggered");

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(diss.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username"); // Retrieve from session attributes

        if (username != null) {
            System.out.println("Username found on disconnection event: " + username);
            System.out.println(username + " disconnected");

            // Create and send a leave message
            Message msg = new Message();
            msg.setSender(username);
            msg.setType('L'); // 'L' for leave
            template.convertAndSend("/topic/main", msg);
        } else {
            System.out.println("No username found in session attributes");
        }
    }



    @EventListener
    public void onConnection(SessionConnectEvent event) {
        System.out.println("On connection event is triggered");

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = accessor.getFirstNativeHeader("username");

        if (username != null) {
            System.out.println("Username from header in onConnection event is: " + username);
            accessor.getSessionAttributes().put("username", username); // Store in session attributes
            System.out.println(username + " is connected");

            // Create and send a join message
            Message msg = new Message();
            msg.setSender(username);
            msg.setType('J'); // 'J' for join
            template.convertAndSend("/topic/main", msg);
        } else {
            System.out.println("No username found in headers");
        }
    }

}
