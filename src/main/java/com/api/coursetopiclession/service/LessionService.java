package com.api.coursetopiclession.service;

import com.api.coursetopiclession.model.Lession;
import com.api.coursetopiclession.model.Topic;
import com.api.coursetopiclession.repository.LessionRepository;
import com.api.coursetopiclession.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessionService {
    @Autowired
    private LessionRepository lessionRepository;
    @Autowired
    private TopicRepository topicRepository;

    public List<Lession> getAllLessions(int courseId, int topicId) {
        List<Lession> lessions = new ArrayList<>();
        List<Topic> topics = new ArrayList<>(topicRepository.findByCourseId(courseId));
        if(topics.stream().anyMatch(t->t.getRef_id()==topicId)){
            Topic topic = topics.stream().filter(t->t.getRef_id()==topicId).findFirst().get();
            lessions = lessionRepository.findByTopicId(topic.getId());
        }
        return lessions;
    }

    public Optional<Lession> getLessions(int id) {
        return lessionRepository.findById(id);
    }

    public void addLession(Lession lession) {
        lessionRepository.save(lession);
    }

    public void updateLession(Lession lession) {
        lessionRepository.save(lession);
    }

    public void deleteLession(int id) {
        lessionRepository.deleteById(id);
    }
}
