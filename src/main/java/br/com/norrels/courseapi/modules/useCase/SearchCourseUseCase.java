package br.com.norrels.courseapi.modules.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.norrels.courseapi.modules.entity.CourseEntity;
import br.com.norrels.courseapi.modules.repository.CourseRepository;

@Service
public class SearchCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute(String name, String category) {
        if (name != null && category != null) {
            return courseRepository.findByNameAndCategory(name, category);
        }

        return courseRepository.findAll();
    }
}
