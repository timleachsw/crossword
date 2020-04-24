package com.tww.crossword.repositories;

import com.tww.crossword.models.Crossword;
import org.springframework.data.repository.CrudRepository;

public interface CrosswordRepository extends CrudRepository<Crossword, Integer> {
}
