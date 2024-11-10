package com.aldemao.lines.configure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.aldemao.lines.entity.Line;
import com.aldemao.lines.repository.LineRepository;

@Configuration
public class DataLoader {

    @Autowired
    private LineRepository lineRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (lineRepository.count() == 0) {
                Line line_1 = new Line();
                Line line_2 = new Line();
                Line line_3 = new Line();
                Line line_4 = new Line();
                Line line_5 = new Line();

                line_1.setLine("3191111111");
                line_2.setLine("3192222222");
                line_3.setLine("3193333333");
                line_4.setLine("3194444444");
                line_5.setLine("3195555555");

                line_1.setAuthorized(true);
                line_2.setAuthorized(true);
                line_3.setAuthorized(true);
                line_4.setAuthorized(true);
                line_5.setAuthorized(true);

                lineRepository.save(line_1);
                lineRepository.save(line_2);
                lineRepository.save(line_3);
                lineRepository.save(line_4);
                lineRepository.save(line_5);
                System.out.println("Lineas por defecto creadas correctamente");
            } else {
                System.out.println("Las lineas por defecto ya se encuentran creadas.");
            }
        };
    }
}
