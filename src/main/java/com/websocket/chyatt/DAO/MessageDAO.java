package com.websocket.chyatt.DAO;

import com.websocket.chyatt.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends JpaRepository<Message,String>{

}
