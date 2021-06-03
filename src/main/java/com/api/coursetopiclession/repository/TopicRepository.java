package com.api.coursetopiclession.repository;

import com.api.coursetopiclession.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    public List<Topic> findByCourseId(int courseId);
}
