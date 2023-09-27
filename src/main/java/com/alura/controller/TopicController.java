package com.alura.controller;


import com.alura.modelo.Course;
import com.alura.modelo.Topic;
import com.alura.modelo.User;
import com.alura.modelo.dto.TopicListData;
import com.alura.modelo.dto.TopicRegisterData;
import com.alura.modelo.dto.TopicResponseData;
import com.alura.modelo.dto.TopicUpdateDataById;
import com.alura.modelo.repositories.CourseRepository;
import com.alura.modelo.repositories.TopicRepository;
import com.alura.modelo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {


    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicController(TopicRepository topicRepository,
                           UserRepository userRepository,
                           CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }


    @PostMapping
    public ResponseEntity<TopicResponseData> topicCreate(@RequestBody @Valid TopicRegisterData topicRegisterData,
                                                             UriComponentsBuilder uriComponentsBuilder) {

        User user = userRepository.findById(Long.parseLong(topicRegisterData.userId()))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Course course = courseRepository.findById(Long.parseLong(topicRegisterData.courseId()))
                .orElseThrow(() -> new RuntimeException("curso no encontrado"));


        if (topicRepository.existsTopicByTitle(topicRegisterData.title()) ||
                topicRepository.existsTopicByMessage(topicRegisterData.message())){
            throw  new RuntimeException("Titulo o mensaje repetido");
        }

        Topic topic = new Topic(topicRegisterData, user, course);
        topicRepository.save(topic);

        TopicResponseData data = new TopicResponseData(topic);

        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(url).body(data);
    }

    @GetMapping
    public ResponseEntity<Page<TopicListData>> topicList(@PageableDefault(size = 2, page = 0,sort = "title") Pageable pageable){
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(TopicListData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicListData>  retornarDatosMedico(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        TopicListData data = new TopicListData(topic);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponseData> actualizarMedico(@PathVariable("id") Long id, @RequestBody @Valid TopicUpdateDataById topicUpdateDataById){

        Course course = courseRepository.findById(Long.parseLong(topicUpdateDataById.courseId()))
                .orElseThrow(() -> new RuntimeException("curso no encontrado"));

        Topic topic = topicRepository.getReferenceById(id);
        topic.updateData(topicUpdateDataById, course);
        TopicResponseData data = new TopicResponseData(topic);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity topicSolved(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.topicSolved();
        TopicResponseData data = new TopicResponseData(topic);
        return ResponseEntity.ok(data);
    }

}