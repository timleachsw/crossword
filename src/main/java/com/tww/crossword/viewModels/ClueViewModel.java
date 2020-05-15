package com.tww.crossword.viewModels;

public class ClueViewModel {
    private String answer;
    private String hint;
    private int clueNumber;

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

    public int getClueNumber() {
        return clueNumber;
    }

    public void setClueNumber(int clueNumber) {
        this.clueNumber = clueNumber;
    }
}
