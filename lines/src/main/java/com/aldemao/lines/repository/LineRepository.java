package com.aldemao.lines.repository;

import com.aldemao.lines.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    Optional<Line> findByLine(String line);
}
