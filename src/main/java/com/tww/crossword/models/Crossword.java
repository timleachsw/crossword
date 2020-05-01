package com.tww.crossword.models;

import javax.persistence.*;
import java.util.Set;

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

    public String getLetterForLocation(int x, int y) {
        for (CrosswordClueInclusion clueInclusion : getClueInclusions()) {
            int clueLength = clueInclusion.getClue().getAnswer().length();
            int clueStartX = clueInclusion.getxPosition();
            int clueStartY = clueInclusion.getyPosition();
            String answer = clueInclusion.getClue().getAnswer();

            if (clueStartX == x && clueStartY == y) {
                return answer.substring(0, 1);
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
        return "";
    }
}
