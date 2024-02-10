package br.com.norrels.courseapi.modules.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.norrels.courseapi.exceptions.CourseNotFound;
import br.com.norrels.courseapi.modules.entity.CourseEntity;
import br.com.norrels.courseapi.modules.repository.CourseRepository;

@Service
public class ChangeCourseAvailability {
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id) {
        var course = courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFound();
        });

        course.setActive(!course.isActive());

        courseRepository.save(course);

        return course;
    }
}
