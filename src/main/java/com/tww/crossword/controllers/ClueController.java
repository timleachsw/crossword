package com.tww.crossword.controllers;

import com.tww.crossword.models.Clue;
import com.tww.crossword.repositories.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/clue")
public class ClueController {

    @Autowired
    private ClueRepository clueRepository;

    @PostMapping
    public @ResponseBody
    String addClue (
            @RequestParam String clue,
            @RequestParam String description,
            @RequestParam String topic,
            @RequestParam String style,
            @RequestParam String author,
            @RequestParam Integer difficulty
    ) {
        Clue newClue = new Clue();
        newClue.setClue(clue);
        newClue.setDescription(description);
        newClue.setTopic(topic);
        newClue.setStyle(style);
        newClue.setAuthor(author);
        newClue.setDifficulty(difficulty);
        clueRepository.save(newClue);
        return "Added clue";
    }

    @GetMapping
    public @ResponseBody
    Iterable<Clue> getAllClues() {
        return clueRepository.findAll();
    }

}
