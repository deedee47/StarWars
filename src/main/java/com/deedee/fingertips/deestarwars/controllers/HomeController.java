package com.deedee.fingertips.deestarwars.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @ApiOperation(value = "Home of Stars")
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home()
    {
        return "Welcome to DeeStarwars!!!";
    }
}
