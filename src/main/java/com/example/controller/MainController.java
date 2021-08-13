package com.example.controller;

import com.example.dto.RoleDto;
import com.example.entity.Role;
import com.example.service.RoleService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping("/")
    public String staticResource(Model model) {
        return "index";
    }

//    @RequestMapping("/")
//    public String variableExample(Model model, HttpServletRequest httpServletRequest) {
//        model.addAttribute("var1", "value var 1");
//        model.addAttribute("var2", "value var 2");
//
//        return "variable-example";
//    }

    @GetMapping("test")
    ResponseEntity<?> test() {
        Role role = roleService.find(1L).get();
        logger.info(role.getName());
        return ResponseEntity.ok(modelMapper.map(role, RoleDto.class));
    }
}
