package com.tww.crossword.services;

import com.tww.crossword.models.Clue;
import com.tww.crossword.models.Crossword;
import com.tww.crossword.repositories.CrosswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CrosswordService {

    @Autowired
    private CrosswordRepository crosswordRepository;

    public void createCrossword(Integer gridSize) {
        Crossword newCrossword = new Crossword();
        newClue.setAnswer(answer);
        newClue.setHint(hint);
        newClue.setTopic(topic);
        newClue.setStyle(style);
        newClue.setAuthor(author);
        newClue.setDifficulty(difficulty);
        clueRepository.save(newClue);
        return ResponseEntity.ok().body(newClue);
    }

}
