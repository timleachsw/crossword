package com.tww.crossword.models;

import javax.persistence.*;

@Entity
public class CrosswordClueInclusion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer xPosition;
    private Integer yPosition;
    private boolean isAcross;

    @ManyToOne
    private Crossword crossword;

    @ManyToOne
    private Clue clue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public boolean getIsAcross() {
        return isAcross;
    }

    public void setIsAcross(boolean isAcross) {
        this.isAcross = isAcross;
    }

    public Crossword getCrossword() {
        return crossword;
    }

    public void setCrossword(Crossword crossword) {
        this.crossword = crossword;
    }

    public Clue getClue() {
        return clue;
    }

    public void setClue(Clue clue) {
        this.clue = clue;
    }
}
