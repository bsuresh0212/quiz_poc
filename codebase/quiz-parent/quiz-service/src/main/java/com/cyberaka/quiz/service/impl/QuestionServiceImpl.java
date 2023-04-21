package com.cyberaka.quiz.service.impl;

import com.cyberaka.quiz.dao.QuestionRepository;
import com.cyberaka.quiz.domain.Question;
import com.cyberaka.quiz.domain.SubTopic;
import com.cyberaka.quiz.domain.Topic;
import com.cyberaka.quiz.service.QuestionService;
import com.cyberaka.quiz.service.SubTopicService;
import com.cyberaka.quiz.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    TopicService topicService;

    @Autowired
    SubTopicService subTopicService;

    @Override
    public Iterable<Question> findQuiz(String topicId, String subTopicId, int level, int count) {
        Topic topic = topicService.findTopic(topicId);
        SubTopic subTopic = subTopicService.findSubTopic(subTopicId);
        List<Question> questions = (List<Question>) questionRepo.findByTopicAndSubTopic(topic, subTopic);
        int total = questions.size();
        Random rand = new Random();
        List<Question> results = new ArrayList<>();
        if (count > total) {
            count = total;
        }
        List<Integer> options = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {

            int index = rand.nextInt(total);
            while (options.contains(index)) {
//                System.err.println("Duplicate Index Ignored >> " + index);
                index = rand.nextInt(total);
            }
            results.add(questions.get(index));
            options.add(index);
//            System.out.println("Index Added >> " + index);
        }
//        System.out.println("Total Questions Selected >> " + results.size());
        return results;
    }

}
