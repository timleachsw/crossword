package com.tww.crossword.repositories;

import com.tww.crossword.models.Clue;
import org.springframework.data.repository.CrudRepository;

public interface ClueRepository extends CrudRepository<Clue, Integer> {
}
