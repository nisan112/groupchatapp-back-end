package com.websocket.chyatt.DAO;

import com.websocket.chyatt.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,String> {
}
