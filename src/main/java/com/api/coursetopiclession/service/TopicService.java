package com.api.coursetopiclession.service;

import com.api.coursetopiclession.model.Topic;
import com.api.coursetopiclession.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics(int courseId) {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findByCourseId(courseId).forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(int id) {
        return topicRepository.findById(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }

}
