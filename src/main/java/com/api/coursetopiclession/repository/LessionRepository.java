package com.api.coursetopiclession.repository;

import com.api.coursetopiclession.model.Lession;
import com.api.coursetopiclession.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessionRepository extends CrudRepository<Lession, Integer> {
    public List<Lession> findByTopicId(int topicId);
}
