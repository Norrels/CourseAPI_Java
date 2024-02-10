package br.com.norrels.courseapi.modules.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.norrels.courseapi.modules.dto.SimpleCourseRequestDTO;
import br.com.norrels.courseapi.modules.entity.CourseEntity;
import br.com.norrels.courseapi.modules.repository.CourseRepository;

@Service
public class UpdateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, SimpleCourseRequestDTO courseRequestDTO) throws Exception {
        var course = this.courseRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException();
        });

        course.setName(courseRequestDTO.getName());
        course.setCategory(courseRequestDTO.getCategory());

        courseRepository.save(course);

        return course;
    }
}