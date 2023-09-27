package com.alura.modelo.dto;

public record TopicUpdateDataById(String title,
                                  String message,
                                  String topicStatus,
                                  String courseId) {
}
