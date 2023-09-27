package com.alura.modelo.dto;

import com.alura.modelo.Topic;


public record TopicResponseData(String title, String message,String topicStatus, String userName, String courseName) {
    public TopicResponseData(Topic topic){
        this(
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus().toString(),
                topic.getUser().getName(),
                topic.getCourse().getName()
        );

    }
}
