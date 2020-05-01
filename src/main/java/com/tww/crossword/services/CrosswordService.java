package com.tww.crossword.services;

import com.tww.crossword.models.Crossword;
import com.tww.crossword.models.CrosswordClueInclusion;
import com.tww.crossword.repositories.CrosswordClueInclusionRepository;
import com.tww.crossword.repositories.CrosswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrosswordService {

    @Autowired
    private CrosswordRepository crosswordRepository;

    @Autowired
    private CrosswordClueInclusionRepository crosswordClueInclusionRepository;

    @Autowired
    private ClueService clueService;

    public Crossword createCrossword(Integer gridSize) {
        Crossword newCrossword = new Crossword();
        newCrossword.setSize(gridSize);
        return crosswordRepository.save(newCrossword);
    }

    public Crossword updateCrossword(Integer crosswordId, Integer clueId, Integer x, Integer y, boolean isAcross) throws Exception {
        CrosswordClueInclusion newCrosswordClueInclusion = new CrosswordClueInclusion();
        newCrosswordClueInclusion.setCrossword(this.getCrossword(crosswordId));
        newCrosswordClueInclusion.setClue(clueService.getClue(clueId));
        newCrosswordClueInclusion.setxPosition(x);
        newCrosswordClueInclusion.setyPosition(y);
        newCrosswordClueInclusion.setIsAcross(isAcross);
        crosswordClueInclusionRepository.save(newCrosswordClueInclusion);
        return this.getCrossword(crosswordId);
    }

    public Crossword getCrossword(Integer crosswordId) throws Exception {
        var crossword = crosswordRepository.findById(crosswordId);
        if (crossword.isEmpty()) {
            throw new Exception();
        }
        return crossword.get();
    }

}
