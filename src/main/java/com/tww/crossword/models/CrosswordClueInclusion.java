package com.tww.crossword.models;

import javax.persistence.*;

@Entity
public class CrosswordClueInclusion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String author;

    @ManyToOne
    private Crossword crossword;

    @ManyToOne
    private Clue clue;
}
