package org.example.bonnesante_api.Controller;

import org.example.bonnesante_api.Entity.UserEntity;
import org.example.bonnesante_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userEntity != null) {
            userEntity.setPassword("");
            return ResponseEntity.ok(userEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userEntity != null) {
            userEntity.setPassword("");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(userService.saveUser(user));
        }
    }
}
