package com.api.coursetopiclession.model;

import javax.persistence.*;

@Entity
public class Lession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int ref_id;
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private Course course;

    public Lession() {
    }

    public Lession(int id, String name, String description, Topic topic, Course course) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = topic;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }
}
