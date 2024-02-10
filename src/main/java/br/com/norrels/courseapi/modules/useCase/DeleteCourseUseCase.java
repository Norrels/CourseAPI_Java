package br.com.norrels.courseapi.modules.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.norrels.courseapi.exceptions.CourseNotFound;
import br.com.norrels.courseapi.modules.repository.CourseRepository;

@Service
public class DeleteCourseUseCase {
    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id) throws Exception {
        this.courseRepository.findById(id).orElseThrow(() -> {
            throw new CourseNotFound();
        });

        this.courseRepository.deleteById(id);
    }
}
