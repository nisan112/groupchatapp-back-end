package com.websocket.chyatt.SocketConfig;
import com.websocket.chyatt.Models.Message;
import com.websocket.chyatt.Models.User;
import com.websocket.chyatt.Services.MessageServices;
import com.websocket.chyatt.Services.TimeConverter;
import com.websocket.chyatt.Services.UserServices;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Component
public class EventHandler {
    private MessageServices messageservices;
    private TimeConverter converter;
    private UserServices userservices;
    private final SimpMessagingTemplate template;

    public EventHandler(SimpMessagingTemplate template, UserServices userservices, TimeConverter converter, MessageServices messageservices) {
        this.template = template;
        this.userservices = userservices;
        this.converter = converter;
        this.messageservices = messageservices;
    }

    @EventListener
    public void onDisconnection(SessionDisconnectEvent diss) {
        System.out.println("On disconnect event is triggered");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(diss.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username"); // Retrieve from session attributes
        String time = (String) accessor.getSessionAttributes().get("joineddate");
        if (username != null) {
            System.out.println("Username found on disconnection event: " + username);
            System.out.println(username + " disconnected");
            userservices.deleteUser(username);
            System.out.println(username +" user deleted");

            // Create and send a leave message
            Message msg = new Message();
            msg.setContent(username + " left the chat");
            msg.setDatetime(time);
            msg.setSender(username);
            msg.setType('L');
            messageservices.saveMessage(msg);
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
        String time = accessor.getFirstNativeHeader("joineddate");

        if (username != null) {
            System.out.println("Username from header in onConnection event is: " + username);
            accessor.getSessionAttributes().put("username", username); // Store in session attributes
            accessor.getSessionAttributes().put("joineddate",time);
            System.out.println(username + " is connected");

            User user = new User(username,time);
            userservices.saveUser(user);

            Message msg = new Message();
            msg.setContent(username + " joined the chat");
            msg.setDatetime(time);
            msg.setSender(username);
            msg.setType('J'); // 'J' for join
            messageservices.saveMessage(msg);

            template.convertAndSend("/topic/main", msg);
        } else {
            System.out.println("No username found in headers");
        }
    }

}
