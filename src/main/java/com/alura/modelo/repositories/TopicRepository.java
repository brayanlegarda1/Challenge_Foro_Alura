package com.alura.modelo.repositories;

import com.alura.modelo.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Boolean existsTopicByTitle(String title);
    Boolean existsTopicByMessage(String message);
}
