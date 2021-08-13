package com.example.controller;

import com.example.dto.UserDto;
import com.example.entity.JwtResponse;
import com.example.entity.User;
import com.example.entity.UserPrinciple;
import com.example.service.JwtService;
import com.example.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
//    @Autowired
//    private ModelMapper modelMapper;


    private final Logger logger = Logger.getLogger(AuthController.class);

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, "1", currentUser.getEmail(), currentUser.getName(), userDetails.getAuthorities()));
    }

    @GetMapping("me")
    public ResponseEntity<?> user() {
        UserPrinciple userDetails = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.find(userDetails.getId());
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user.get(), UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    public String printObject(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}
