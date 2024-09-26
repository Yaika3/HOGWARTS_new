package com.example.hogwarts.controller;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Info")
@RestController
public class InfoController {
    private ServerProperties serverProperties;
    @GetMapping("/getPort")
    public void getPort(){
        int port = serverProperties.getPort();
    }



}
