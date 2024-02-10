package br.com.norrels.courseapi.modules.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.norrels.courseapi.modules.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findByNameAndCategory(String name, String category);
}