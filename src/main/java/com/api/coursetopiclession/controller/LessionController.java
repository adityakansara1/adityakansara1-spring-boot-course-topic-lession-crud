package com.api.coursetopiclession.controller;

import com.api.coursetopiclession.model.Course;
import com.api.coursetopiclession.model.Lession;
import com.api.coursetopiclession.model.Topic;
import com.api.coursetopiclession.service.LessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessionController {

    @Autowired
    private LessionService lessionService;
    @Autowired
    private TopicController topicController;

    @GetMapping("/courses/{courseId}/topics/{topicId}/lessions")
    public List<Lession> getAllLessions(@PathVariable int courseId, @PathVariable int topicId){
        return lessionService.getAllLessions(courseId, topicId);
    }

    @GetMapping("/courses/{courseId}/topics/{topicId}/lessions/{lessionId}")
    public Lession getLession(@PathVariable int courseId, @PathVariable int topicId, @PathVariable int lessionId){
        if(getAllLessions(courseId, topicId).stream().anyMatch(l -> l.getRef_id() == lessionId)) {
            return getAllLessions(courseId, topicId).stream().filter(l -> l.getRef_id() == lessionId).findFirst().get();
        } else {
            return null;
        }
    }

    @PostMapping("/courses/{courseId}/topics/{topicId}/lessions")
    public String addLession(@RequestBody Lession lession, @PathVariable int courseId, @PathVariable int topicId) {
        lession.setCourse(new Course(courseId, "", ""));
        Topic topic = topicController.getTopic(courseId, topicId);
        lession.setTopic(new Topic(topic.getId(), "", "", topicId, new Course(courseId, "", "")));
        lessionService.addLession(lession);
        return "Successfully Added";
    }

    @PutMapping("/courses/{courseId}/topics/{topicId}/lessions")
    public String updateTopic(@RequestBody Lession lession, @PathVariable int courseId, @PathVariable int topicId){
        lession.setCourse(new Course(courseId, "", ""));
        Topic topic = topicController.getTopic(courseId, topicId);
        lession.setTopic(new Topic(topic.getId(), "", "", new Course(courseId, "", "")));
        lessionService.updateLession(lession);
        return "Successfully updated";
    }

    @DeleteMapping("/courses/{courseId}/topics//{topicId}/lessions/{lessionId}")
    public String deleteTopic(@PathVariable int courseId, @PathVariable int topicId, @PathVariable int lessionId) {
        if(getAllLessions(courseId, topicId).stream().anyMatch(l -> l.getRef_id() == lessionId)) {
            Lession lession = getAllLessions(courseId,topicId).stream().filter(l -> l.getRef_id() == lessionId).findFirst().get();
            int id = lession.getId();
            lessionService.deleteLession(id);
            return "Successfully deleted";
        } else {
            return "Topic doesn't exists";
        }
    }
}
