package com.tww.crossword.services;

import com.tww.crossword.models.Clue;
import com.tww.crossword.repositories.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClueService {
    @Autowired
    private ClueRepository clueRepository;

    public Clue getClue(Integer clueId) throws Exception {
        var clue = clueRepository.findById(clueId);
        if (clue.isEmpty()) {
            throw new Exception();
        }
        return clue.get();
    }
}
