package com.tww.crossword.controllers;

import com.tww.crossword.models.Clue;
import com.tww.crossword.repositories.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/clue")
public class ClueController {

    @Autowired
    private ClueRepository clueRepository;

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
        Clue newClue = new Clue();
        newClue.setAnswer(answer);
        newClue.setHint(hint);
        newClue.setTopic(topic);
        newClue.setStyle(style);
        newClue.setAuthor(author);
        newClue.setDifficulty(difficulty);
        clueRepository.save(newClue);
        return ResponseEntity.ok().body(newClue);
    }

    @GetMapping
    public @ResponseBody
    Iterable<Clue> getAllClues() {
        return clueRepository.findAll();
    }

}
