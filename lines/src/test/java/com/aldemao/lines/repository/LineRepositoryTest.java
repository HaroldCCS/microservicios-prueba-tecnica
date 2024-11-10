package com.aldemao.lines.repository;

import com.aldemao.lines.entity.Line;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LineRepositoryTest {
    @Autowired
    private LineRepository lineRepository;

    @Test
    void saveMethod() {
        Line line = new Line();
        line.setLine("3192314936");
        Line saved = lineRepository.save(line);

        System.out.println(saved.getId());
        System.out.println(saved.toString());
    }

}