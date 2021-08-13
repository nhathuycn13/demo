package com.example.controller;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private final ModelMapper modelMapper;
    private static Logger logger = Logger.getLogger(UserController.class);

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> user = (List<User>) userService.getAll();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> store(@RequestBody User user) {
        User u = userService.save(user);

        return ResponseEntity.ok(modelMapper.map(u, UserDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable String id) {
        Optional<User> user = userService.find(Long.valueOf(id));
        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        UserDto userDto = modelMapper.map(user.get(), UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Optional<User> userResult = userService.find(id);
        if (userResult.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(modelMapper.map(userResult.get(), UserDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> destroy(@PathVariable Long id) {
        userService.destroy(id);
        return ResponseEntity.ok().build();
    }
}
