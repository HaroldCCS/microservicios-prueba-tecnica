package com.aldemao.lines.service;

import com.aldemao.lines.entity.Line;
import com.aldemao.lines.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepository;


    public boolean validateExistence (String line) {
        Line filter = new Line();
        filter.setLine(line);
        Example<Line> example = Example.of(filter);

        return lineRepository.exists(example);
    }


    public boolean validateAuthorization (String line) {
        Line filter = new Line();
        filter.setLine(line);
        filter.setAuthorized(true);
        Example<Line> example = Example.of(filter);

        return lineRepository.exists(example);
    }
}
