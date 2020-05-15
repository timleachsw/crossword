package com.tww.crossword.services;

import com.tww.crossword.models.Clue;
import com.tww.crossword.models.Crossword;
import com.tww.crossword.repositories.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.thymeleaf.util.StringUtils.concat;

@Service
public class ClueService {

    @Autowired
    private ClueRepository clueRepository;

    @Autowired
    private CrosswordService crosswordService;

    public Clue getClue(Integer clueId) throws Exception {
        var clue = clueRepository.findById(clueId);
        if (clue.isEmpty()) {
            throw new Exception();
        }
        return clue.get();
    }

    public Clue createClue(String answer, String hint, String topic, String style, String author, Integer difficulty) {
        Clue newClue = new Clue();
        newClue.setAnswer(answer);
        newClue.setHint(hint);
        newClue.setTopic(topic);
        newClue.setStyle(style);
        newClue.setAuthor(author);
        newClue.setDifficulty(difficulty);
        clueRepository.save(newClue);
        return newClue;
    }

    public List<List<Clue>> getValidClues(Integer x, Integer y, Integer crosswordId) throws Exception {
        Crossword crossword = crosswordService.getCrossword(crosswordId);
        List<Clue> validAcrossClues = getValidClues(x, y, crossword, true);
        List<Clue> validDownClues = getValidClues(x, y, crossword, false);
        return new ArrayList<>(Arrays.asList(validAcrossClues, validDownClues));
    }

    public Iterable<Clue> getAllClues() {
        return clueRepository.findAll();
    }

    private List<Clue> getValidClues(Integer x, Integer y, Crossword crossword, boolean isAcross) {
        Iterable<Clue> clues = getAllClues();
        Stream<Clue> clueStream = StreamSupport.stream(clues.spliterator(), false);
        List<Clue> validClues = clueStream.filter(clue -> isValidClue(clue, x, y, crossword, isAcross)).collect(Collectors.toList());
        return validClues;
    }

    private boolean isValidClue(Clue clue, Integer x, Integer y, Crossword crossword, boolean isAcross) {
        if (isClueTooLong(clue.getAnswer().length(), x, y, crossword, isAcross)) { return false; }
        String[] letters = clue.getAnswer().split("");
        if (!isValidFirstCharacter(letters[0], x, y, crossword)) { return false; }
        for (int i = 1; i < letters.length; i++) {
            boolean isValid = isAcross ?
                    isValidAcrossCharacter(letters[i], x+i, y, crossword) :
                    isValidDownCharacter(letters[i], x, y+i, crossword);
            if (!isValid) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidAcrossCharacter(String newLetter, Integer x, Integer y, Crossword crossword) {
        newLetter = newLetter.toLowerCase();
        String existingLetter = crossword.getLetterForLocation(x, y).toLowerCase();
        if (existingLetter.isEmpty()) {
            String down = crossword.getLetterForLocation(Math.min(crossword.getSize(),x+1), y);
            String left = crossword.getLetterForLocation(x, Math.max(0,y-1));
            String right = crossword.getLetterForLocation(x, Math.min(crossword.getSize(),y+1));
            String all = concat(down, left, right);
            return all.isEmpty();
        }
        return existingLetter.equals(newLetter);
    }

    private boolean isValidDownCharacter(String newLetter, Integer x, Integer y, Crossword crossword) {
        newLetter = newLetter.toLowerCase();
        String existingLetter = crossword.getLetterForLocation(x, y).toLowerCase();
        if (existingLetter.isEmpty()) {
            String up = crossword.getLetterForLocation(Math.max(0,x-1), y);
            String down = crossword.getLetterForLocation(Math.min(crossword.getSize(),x+1), y);
            String right = crossword.getLetterForLocation(x, Math.min(crossword.getSize(),y+1));
            String all = concat(down, up, right);
            return all.isEmpty();
        }
        return existingLetter.equals(newLetter);
    }

    private boolean isValidFirstCharacter(String newLetter, Integer x, Integer y, Crossword crossword) {
        newLetter = newLetter.toLowerCase();
        String existingLetter = crossword.getLetterForLocation(x, y).toLowerCase();
        if (existingLetter.isEmpty()) {
            String up = crossword.getLetterForLocation(Math.max(0,x-1), y);
            String down = crossword.getLetterForLocation(Math.min(crossword.getSize(),x+1), y);
            String left = crossword.getLetterForLocation(x, Math.max(0,y-1));
            String right = crossword.getLetterForLocation(x, Math.min(crossword.getSize(),y+1));
            String all = concat(up, down, left, right);
            return all.isEmpty();
        }
        return existingLetter.equals(newLetter);
    }

    private boolean isClueTooLong(Integer clueLength, Integer x, Integer y, Crossword crossword, boolean isAcross) {
        return isAcross ? x + clueLength > crossword.getSize() : y + clueLength > crossword.getSize();
    }
}
