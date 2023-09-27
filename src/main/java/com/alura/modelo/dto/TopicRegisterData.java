package com.alura.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegisterData(
        @NotBlank(message = "El título es obligatorio")
        String title,
        @NotBlank(message = "El contenido es obligatorio")
        String message,
        @NotBlank(message = "Se debe asociar un usuario")
        String userId,
        @NotNull(message = "Se debe asociar un curso")
        String courseId

) {

}
