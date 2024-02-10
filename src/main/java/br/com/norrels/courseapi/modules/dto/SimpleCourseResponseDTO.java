package br.com.norrels.courseapi.modules.dto;

import java.util.UUID;

public record SimpleCourseResponseDTO(UUID id, String name, String category, boolean active) {

}
