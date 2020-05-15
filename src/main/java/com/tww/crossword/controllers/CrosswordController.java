package com.tww.crossword.controllers;

import com.tww.crossword.models.Crossword;
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
        Crossword crossword = crosswordService.createCrossword(gridSize);
        model.addAttribute("crossword", crossword);
        model.addAttribute("crosswordVM", crossword.getCrosswordViewModel());
        return "crossword";
    }

    @GetMapping
    public String updateCrossword(
            @RequestParam(name="crosswordId", required=false, defaultValue="13") Integer crosswordId,
            @RequestParam(name="clueId", required=false, defaultValue="13") Integer clueId,
            @RequestParam(name="x", required=false, defaultValue="13") Integer x,
            @RequestParam(name="y", required=false, defaultValue="13") Integer y,
            @RequestParam(name="isAcross", required=false, defaultValue="13") boolean isAcross,
            Model model) throws Exception {
        Crossword crossword = crosswordService.updateCrossword(crosswordId, clueId, x, y, isAcross);
        model.addAttribute("crossword", crossword);
        model.addAttribute("crosswordVM", crossword.getCrosswordViewModel());
        return "crossword";
    }
}
