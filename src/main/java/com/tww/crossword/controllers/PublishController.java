package com.tww.crossword.controllers;

import com.tww.crossword.models.Crossword;
import com.tww.crossword.services.CrosswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/publish")
public class PublishController {

    @Autowired
    private CrosswordService crosswordService;

    @GetMapping
    public String publishCrossword(
            @RequestParam(name="crosswordId") Integer crosswordId,
            Model model) throws Exception {
        Crossword crossword = crosswordService.getCrossword(crosswordId);
        model.addAttribute("crossword", crossword);
        model.addAttribute("acrossClues", crossword.getAcrossClues());
        model.addAttribute("downClues", crossword.getDownClues());
        return "publish";
    }
}
