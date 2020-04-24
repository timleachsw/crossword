package com.tww.crossword.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/crossword")
public class CrosswordController {

    @GetMapping
    public String crossword(@RequestParam(name="gridSize", required=false, defaultValue="13") String gridSize, Model model) {
        model.addAttribute("gridSize", Integer.parseInt(gridSize));
        System.out.println("NOW IN THIS FUNCTION");
        return "crossword";
    }
}
