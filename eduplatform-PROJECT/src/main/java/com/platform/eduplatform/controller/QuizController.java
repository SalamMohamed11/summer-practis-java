package com.platform.eduplatform.controller;

import com.platform.eduplatform.model.QuestionForm;
import com.platform.eduplatform.model.Result;
import com.platform.eduplatform.service.QuizService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Show home page
    @GetMapping("/")
    public String homePage() {
        return "index";  // index.html
    }

    // Start quiz after submitting username
    @PostMapping("/quiz")
    public String startQuiz(@RequestParam("username") String username, Model model, HttpSession session) {
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("warning", "Name is required");
            return "index";
        }

        session.setAttribute("username", username);
        QuestionForm qForm = quizService.getQuestions();
        model.addAttribute("qForm", qForm);

        return "quiz";  // quiz.html
    }

    // Submit quiz and show result
    @PostMapping("/submit")
    public String submitQuiz(@ModelAttribute QuestionForm qForm, Model model, HttpSession session) {
        int correctAnswers = quizService.getResult(qForm);

        Result result = new Result();
        result.setUsername((String) session.getAttribute("username"));
        result.setTotalCorrect(correctAnswers);

        quizService.saveScore(result);

        model.addAttribute("result", result);
        return "result";  // result.html
    }

    // Show scoreboard
    @GetMapping("/score")
    public String showScoreboard(Model model) {
        model.addAttribute("sList", quizService.getTopScore());
        return "score";  // score.html
    }
}
