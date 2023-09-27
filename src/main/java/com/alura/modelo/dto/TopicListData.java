package com.alura.modelo.dto;

import com.alura.modelo.Topic;

public record TopicListData(String title, String message, String creationDate,
                            String status, String user, String course) {
    public TopicListData(Topic topic){
        this(topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate().toString(),
                topic.getStatus().toString(),
                topic.getUser().getName(),
                topic.getCourse().getName());
    }
}