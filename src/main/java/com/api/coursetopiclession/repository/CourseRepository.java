package com.api.coursetopiclession.repository;

import com.api.coursetopiclession.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
