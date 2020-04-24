package com.tww.crossword.controllers;

import com.tww.crossword.services.CrosswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/crossword")
public class CrosswordController {

    @Autowired
    private CrosswordService crosswordService;

    @GetMapping(path="/new")
    public String createCrossword(@RequestParam(name="gridSize", required=false, defaultValue="13") Integer gridSize, Model model) {
        crosswordService.createCrossword(gridSize);
        model.addAttribute("gridSize", gridSize);
        return "crossword";
    }

    @GetMapping
    public String updateCrossword(
            @RequestParam(name="id", required=false, defaultValue="13") Integer id,
            @RequestParam(name="clue", required=false, defaultValue="13") String clue,
            @RequestParam(name="clue", required=false, defaultValue="13") Integer x,
            @RequestParam(name="clue", required=false, defaultValue="13") Integer y,
            @RequestParam(name="clue", required=false, defaultValue="13") Boolean isAcross,
            Model model) {
        CrosswordService.updateCrossword(id, clue, x, y, isAcross);
        return "crossword";
    }
}
