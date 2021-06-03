package com.api.coursetopiclession.controller;

import com.api.coursetopiclession.model.Course;
import com.api.coursetopiclession.model.Topic;
import com.api.coursetopiclession.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/courses/{courseId}/topics")
    public List<Topic> getAllTopics(@PathVariable int courseId){
        return topicService.getAllTopics(courseId);
    }

    @GetMapping("/courses/{courseId}/topics/{topicId}")
    public Topic getTopic(@PathVariable int courseId, @PathVariable int topicId){
        boolean isPresent = getAllTopics(courseId).stream().anyMatch(t -> t.getRef_id() == topicId);
        if(isPresent) {
            return getAllTopics(courseId).stream().filter(t -> t.getRef_id() == topicId).findFirst().get();
        } else {
            return null;
        }
    }

    @PostMapping("/courses/{courseId}/topics")
    public String addTopic(@RequestBody Topic topic, @PathVariable int courseId) {
        topic.setCourse(new Course(courseId, "", ""));
        topicService.addTopic(topic);
        return "Successfully Added";
    }

    @PutMapping("/courses/{courseId}/topics")
    public String updateTopic(@RequestBody Topic topic, @PathVariable int courseId){
        topic.setCourse(new Course(courseId, "", ""));
        topicService.updateTopic(topic);
        return "Successfully updated";
    }

    @DeleteMapping("/courses/{courseId}/topics/{topicId}")
    public String deleteTopic(@PathVariable int courseId, @PathVariable int topicId) {
        Topic topic;
        if(getAllTopics(courseId).stream().anyMatch(t -> t.getRef_id() == topicId)) {
            topic = getAllTopics(courseId).stream().filter(t -> t.getRef_id() == topicId).findFirst().get();
            int id = topic.getId();
            topicService.deleteTopic(id);
            return "Successfully deleted";
        } else {
            return "Topic doesn't exists";
        }
    }
}