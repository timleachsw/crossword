package com.tww.crossword.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String answer;
    private String hint;
    private String topic;
    private String style;
    private String author;
    private Integer difficulty;

    @OneToMany(mappedBy = "clue")
    private Set<CrosswordClueInclusion> clueInclusions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Set<CrosswordClueInclusion> getClueInclusions() {
        return clueInclusions;
    }

    public void setClueInclusions(Set<CrosswordClueInclusion> clueInclusions) {
        this.clueInclusions = clueInclusions;
    }
}
