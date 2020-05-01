package com.tww.crossword.controllers;

import com.tww.crossword.models.Clue;
import com.tww.crossword.services.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/clue")
public class ClueController {

    @Autowired
    private ClueService clueService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<Clue> addClue (
            @RequestParam String answer,
            @RequestParam String hint,
            @RequestParam String topic,
            @RequestParam String style,
            @RequestParam String author,
            @RequestParam Integer difficulty
    ) {
        Clue newClue = clueService.createClue(answer, hint, topic, style, author, difficulty);
        return ResponseEntity.ok().body(newClue);
    }

    @PostMapping(path="somethingpathy")
    public String getValidClues (
            @RequestParam Integer x,
            @RequestParam Integer y,
            @RequestParam Integer crosswordId,
            Model model
    ) throws Exception {
        List<List<Clue>> validClues = clueService.getValidClues(x, y, crosswordId);
        model.addAttribute("validClues", validClues);
        return "somethingcluey";
    }

    @GetMapping
    public @ResponseBody
    Iterable<Clue> getAllClues() {
        return clueService.getAllClues();
    }

}
