package com.platform.eduplatform.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.platform.eduplatform.model.Question;
import com.platform.eduplatform.model.QuestionForm;
import com.platform.eduplatform.model.Result;
import com.platform.eduplatform.repository.QuestionRepo;
import com.platform.eduplatform.repository.ResultRepo;


@Service
public class QuizService {

    @Autowired
    private QuestionRepo qRepo;

    @Autowired
    private ResultRepo rRepo;

    public QuestionForm getQuestions() {
        List<Question> allQues = qRepo.findAll();
        List<Question> qList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5 && !allQues.isEmpty(); i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        QuestionForm qForm = new QuestionForm();
        qForm.setQuestions(qList);
        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for (Question q : qForm.getQuestions()) {
            if (q.getAns() == q.getChose())
                correct++;
        }

        return correct;
    }

    public void saveScore(Result result) {
        rRepo.save(result);
    }

    public List<Result> getTopScore() {
        return rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
