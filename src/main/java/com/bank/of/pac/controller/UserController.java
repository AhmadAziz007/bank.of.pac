package com.bank.of.pac.controller;

import com.bank.of.pac.dto.MstUserDTO;
import com.bank.of.pac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody MstUserDTO request){
        return userService.createUser(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody MstUserDTO request){
        return userService.updateUser(request);
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getall(){
        return userService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
