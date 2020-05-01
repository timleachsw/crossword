package com.tww.crossword.controllers;

import com.tww.crossword.models.Clue;
import com.tww.crossword.models.Crossword;
import com.tww.crossword.repositories.ClueRepository;
import com.tww.crossword.repositories.CrosswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/add_clue")
public class AddClueToCrosswordController {

    @Autowired
    private ClueRepository clueRepository;

    @Autowired
    private CrosswordRepository crosswordRepository;

    @GetMapping
    public String crossword(
            @RequestParam(name="crossword_id", required=true) Integer crosswordId,
            @RequestParam(name="x_position", required=true) Integer xPosition,
            @RequestParam(name="y_position", required=true) Integer yPosition,
            Model model
    ) {
        // Change, later, to only the clues that will fit here
        Iterable<Clue> allClues = clueRepository.findAll();
        Optional<Crossword> crossword = crosswordRepository.findById(crosswordId);

        if (crossword.isEmpty()) {
            // TODO: tell the user why there's an error
            return "error";
        }

        model.addAttribute("gridSize", crossword.get().getSize());
        model.addAttribute("crosswordId", crosswordId);
        model.addAttribute("xPosition", xPosition);
        model.addAttribute("yPosition", yPosition);
        model.addAttribute("clues", allClues);
        return "add_clue";
    }
}