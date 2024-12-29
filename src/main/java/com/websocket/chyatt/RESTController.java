package com.websocket.chyatt;
import com.websocket.chyatt.Services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RESTController {
    private UserServices userservices;

    public RESTController(UserServices userservices) {
        this.userservices = userservices;
    }

    @GetMapping("/authenticateuser")
    public ResponseEntity<String> authenticateUser(@RequestParam String username, @RequestParam String joineddate){
        if(userservices.userExist(username)){
            return ResponseEntity.status(401).body("This Username is already taken for now");
        }
        else{
            return ResponseEntity.ok("User saved");

        }

    }
}
