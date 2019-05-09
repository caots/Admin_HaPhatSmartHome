package com.bksoftware.sellingweb.service.news;

import com.bksoftware.sellingweb.entities.news.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> findAllTopic();

    Topic findById(int id);

    boolean saveTopic(Topic topic);

    boolean deleteTopic(Topic topic);
}
