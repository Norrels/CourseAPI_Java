package br.com.norrels.courseapi.modules.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.norrels.courseapi.modules.dto.SimpleCourseRequestDTO;
import br.com.norrels.courseapi.modules.entity.CourseEntity;
import br.com.norrels.courseapi.modules.repository.CourseRepository;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public UUID excute(SimpleCourseRequestDTO courseEntity) {

        var course = CourseEntity.builder()
                .active(true)
                .name(courseEntity.getName())
                .category(courseEntity.getCategory())
                .build();

        return this.courseRepository.save(course).getId();

    }
}
