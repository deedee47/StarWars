package com.deedee.fingertips.deestarwars.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home()
    {
        return "Welcome to DeeStarwars";
    }
}
