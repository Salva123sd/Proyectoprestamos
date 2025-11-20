package com.mibanquito.infraestructura.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingControlador {

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}
