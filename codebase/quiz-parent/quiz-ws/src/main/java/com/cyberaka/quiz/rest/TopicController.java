package com.cyberaka.quiz.rest;

import com.cyberaka.quiz.domain.Topic;
import com.cyberaka.quiz.dto.TopicDto;
import com.cyberaka.quiz.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class TopicController {

    private Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    TopicService topicService;

    @RequestMapping("/topics")
    @ResponseBody
    public List<TopicDto> listTopics() {
        log.info("listTopics()");
        Iterable<Topic> topics = topicService.findAll();
        List<TopicDto> results = new ArrayList<TopicDto>();
        for (Topic topic : topics) {
            TopicDto dto = new TopicDto();
            dto.clone(topic);
            results.add(dto);
        }
        return results;
    }
}
