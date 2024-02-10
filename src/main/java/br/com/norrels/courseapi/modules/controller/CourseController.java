package br.com.norrels.courseapi.modules.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.norrels.courseapi.modules.dto.SimpleCourseRequestDTO;
import br.com.norrels.courseapi.modules.dto.SimpleCourseResponseDTO;
import br.com.norrels.courseapi.modules.entity.CourseEntity;
import br.com.norrels.courseapi.modules.useCase.ChangeCourseAvailability;
import br.com.norrels.courseapi.modules.useCase.CreateCourseUseCase;
import br.com.norrels.courseapi.modules.useCase.DeleteCourseUseCase;
import br.com.norrels.courseapi.modules.useCase.SearchCourseUseCase;
import br.com.norrels.courseapi.modules.useCase.UpdateCourseUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private SearchCourseUseCase searchCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private ChangeCourseAvailability changeCourseAvailability;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody SimpleCourseRequestDTO courseRequestDTO) {
        var courseId = createCourseUseCase.excute(courseRequestDTO);

        var response = new HashMap<String, UUID>();
        response.put("id", courseId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public List<CourseEntity> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {

        return searchCourseUseCase.execute(name, category);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id,
            @Valid @RequestBody SimpleCourseRequestDTO courseRequestDTO) {
        try {
            var course = updateCourseUseCase.execute(id, courseRequestDTO);

            var courseResponseDTO = new SimpleCourseResponseDTO(
                    course.getId(),
                    course.getName(),
                    course.getCategory(),
                    course.isActive());

            return ResponseEntity.ok(courseResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteCourseUseCase.execute(id);
            return ResponseEntity.status(204).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> updateCourseavailability(@PathVariable UUID id) {
        try {
            var course = this.changeCourseAvailability.execute(id);

            var courseDTO = new SimpleCourseResponseDTO(
                    course.getId(),
                    course.getName(),
                    course.getCategory(),
                    course.isActive());

            return ResponseEntity.ok(courseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
