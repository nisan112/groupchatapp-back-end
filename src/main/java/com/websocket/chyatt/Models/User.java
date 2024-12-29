package com.websocket.chyatt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private String username;
    private String joineddate;

    public User(String username, String joineddate) {
        this.username = username;
        this.joineddate = joineddate;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoineddate() {
        return joineddate;
    }

    public void setJoineddate(String joineddate) {
        this.joineddate = joineddate;
    }
}
