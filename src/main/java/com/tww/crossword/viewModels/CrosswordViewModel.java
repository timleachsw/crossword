package com.tww.crossword.viewModels;

import java.util.List;

public class CrosswordViewModel {
    private List<SquareViewModel> squares;
    private List<ClueViewModel> acrossClues;
    private List<ClueViewModel> downClues;

    public List<SquareViewModel> getSquares() {
        return squares;
    }

    public void setSquares(List<SquareViewModel> squares) {
        this.squares = squares;
    }

    public List<ClueViewModel> getAcrossClues() {
        return acrossClues;
    }

    public void setAcrossClues(List<ClueViewModel> acrossClues) {
        this.acrossClues = acrossClues;
    }

    public List<ClueViewModel> getDownClues() {
        return downClues;
    }

    public void setDownClues(List<ClueViewModel> downClues) {
        this.downClues = downClues;
    }
}
