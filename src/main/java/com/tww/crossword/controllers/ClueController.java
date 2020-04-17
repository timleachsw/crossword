package com.tww.crossword.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/clue")
public class ClueController {

    @GetMapping
    public @ResponseBody String sayHello() { return "Hello"; }
}
