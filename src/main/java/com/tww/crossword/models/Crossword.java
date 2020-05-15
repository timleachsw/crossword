package com.tww.crossword.models;

import com.tww.crossword.viewModels.ClueViewModel;
import com.tww.crossword.viewModels.CrosswordViewModel;
import com.tww.crossword.viewModels.SquareViewModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Crossword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String author;
    private Integer size;

    @OneToMany(mappedBy = "crossword")
    private Set<CrosswordClueInclusion> clueInclusions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @OneToMany(mappedBy = "crossword")
    public Set<CrosswordClueInclusion> getClueInclusions() {
        return clueInclusions;
    }

    public void setClueInclusions(Set<CrosswordClueInclusion> clueInclusions) {
        this.clueInclusions = clueInclusions;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Clue> getAcrossClues() {
        return this.clueInclusions.stream().filter(CrosswordClueInclusion::getIsAcross)
                .map(CrosswordClueInclusion::getClue)
                .collect(Collectors.toList());
    }

    public List<Clue> getDownClues() {
        return this.clueInclusions.stream().filter(clueInclusion -> !clueInclusion.getIsAcross())
                .map(CrosswordClueInclusion::getClue)
                .collect(Collectors.toList());
    }

    public CrosswordViewModel getCrosswordViewModel() {
        CrosswordViewModel crossword = new CrosswordViewModel();
        List<SquareViewModel> squares = new ArrayList<>();
        List<ClueViewModel> acrossClues = new ArrayList<>();
        List<ClueViewModel> downClues = new ArrayList<>();
        int x, y;
        int count = 1;
        for (int i = 1; i <= this.size*this.size; i++) {
            SquareViewModel square = new SquareViewModel();
            square.setPosition(i);
            x = (i - 1) % this.size;
            y = ((i - 1) - ((i - 1) % this.size)) / this.size;
            String letter = getLetterForLocation(x, y);
            if (!letter.equals("") && letter.toUpperCase().equals(letter)) {
                square.setNumber(count);
                List<CrosswordClueInclusion> clueInclusionsForLocation = this.getClueInclusionsForLocation(x, y);
                if (clueInclusionsForLocation.size() > 0) {
                    int finalX = x;
                    int finalY = y;
                    int finalCount = count;
                    clueInclusionsForLocation.forEach(clueInclusion -> {
                        if (clueInclusion.getxPosition() == finalX && clueInclusion.getyPosition() == finalY) {
                            Clue clue = clueInclusion.getClue();
                            ClueViewModel clueViewModel = new ClueViewModel();
                            clueViewModel.setAnswer(clue.getAnswer());
                            clueViewModel.setHint(clue.getHint());
                            clueViewModel.setClueNumber(finalCount);
                            if (clueInclusion.getIsAcross()) {
                                acrossClues.add(clueViewModel);
                            } else {
                                downClues.add(clueViewModel);
                            }
                        }
                    });
                }
                count++;
            }
            square.setLetter(letter.toLowerCase());
            squares.add(square);
        }
        crossword.setSquares(squares);
        crossword.setAcrossClues(acrossClues);
        crossword.setDownClues(downClues);
        return crossword;
    }

    private List<CrosswordClueInclusion> getClueInclusionsForLocation(int x, int y) {
        List<CrosswordClueInclusion> clueInclusions = new ArrayList<>();
        if (this.clueInclusions != null) {
            for (CrosswordClueInclusion clueInclusion : this.clueInclusions) {
                int clueLength = clueInclusion.getClue().getAnswer().length();
                int clueStartX = clueInclusion.getxPosition();
                int clueStartY = clueInclusion.getyPosition();

                if (clueStartX == x && clueStartY == y) {
                    clueInclusions.add(clueInclusion);
                } else if (clueStartX == x && clueStartY < y && !clueInclusion.getIsAcross()) {
                    int relativeY = y - clueStartY;
                    if (relativeY < clueLength) {
                        clueInclusions.add(clueInclusion);
                    }
                } else if (clueStartX < x && clueStartY == y && clueInclusion.getIsAcross()) {
                    int relativeX = x - clueStartX;
                    if (relativeX < clueLength) {
                        clueInclusions.add(clueInclusion);
                    }
                }
            }
        }
        return clueInclusions;
    }

    public String getLetterForLocation(int x, int y) {
        if (clueInclusions != null) {
            for (CrosswordClueInclusion clueInclusion : clueInclusions) {
                int clueLength = clueInclusion.getClue().getAnswer().length();
                int clueStartX = clueInclusion.getxPosition();
                int clueStartY = clueInclusion.getyPosition();
                String answer = clueInclusion.getClue().getAnswer();

                if (clueStartX == x && clueStartY == y) {
                    return answer.substring(0, 1).toUpperCase();
                } else if (clueStartX == x && clueStartY < y && !clueInclusion.getIsAcross()) {
                    int relativeY = y - clueStartY;
                    if (relativeY < clueLength) {
                        return answer.substring(relativeY, relativeY + 1);
                    }
                } else if (clueStartX < x && clueStartY == y && clueInclusion.getIsAcross()) {
                    int relativeX = x - clueStartX;
                    if (relativeX < clueLength) {
                        return answer.substring(relativeX, relativeX + 1);
                    }
                }
            }
        }
        return "";
    }
}
