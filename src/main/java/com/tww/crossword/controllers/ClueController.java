package com.tww.crossword.controllers;

import com.tww.crossword.models.Clue;
import com.tww.crossword.services.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
