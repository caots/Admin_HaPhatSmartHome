package com.bksoftware.sellingweb.service_impl.news;

import com.bksoftware.sellingweb.entities.news.Topic;
import com.bksoftware.sellingweb.repository.news.TopicRepository;
import com.bksoftware.sellingweb.service.news.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TopicService_Impl implements TopicService {

    Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> findAllTopic() {
        try {
            List<Topic> topics = topicRepository.findAll();
            return topics.stream()
                    .filter(p -> (p.isStatus() == true))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-topic-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Topic findById(int id) {
        try {
            Topic topic = topicRepository.findById(id);
            if (topic.isStatus() == true) return topic;
            return null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-topic-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveTopic(Topic topic) {
        try {
            topicRepository.save(topic);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-topic-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTopic(Topic topic) {
        try {
            topic.setStatus(false);
            topicRepository.save(topic);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-topic-error : {0}", ex.getMessage());
        }
        return false;
    }
}
