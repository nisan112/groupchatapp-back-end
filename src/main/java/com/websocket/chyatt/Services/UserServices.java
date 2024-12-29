package com.websocket.chyatt.Services;

import com.websocket.chyatt.DAO.UserDAO;
import com.websocket.chyatt.Models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private UserDAO userdao;

    public UserServices(UserDAO userdao) {
        this.userdao = userdao;
    }

    public void saveUser(User user){
        userdao.save(user);

    }
    public boolean userExist(String id){
        return userdao.existsById(id);
    }

    public void deleteUser(String username){
        userdao.deleteById(username);
    }
}
