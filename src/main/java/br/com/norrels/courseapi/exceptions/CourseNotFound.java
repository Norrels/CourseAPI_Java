package br.com.norrels.courseapi.exceptions;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound() {
        super("Curso não encontrado");
    }
}