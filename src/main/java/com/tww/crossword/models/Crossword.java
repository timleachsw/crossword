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

    public Set<CrosswordClueInclusion> getClueInclusions() {
        return clueInclusions;
    }

    public void setClueInclusions(Set<CrosswordClueInclusion> clueInclusions) {
        this.clueInclusions = clueInclusions;
    }
}
