package com.platform.eduplatform.repository;

import com.platform.eduplatform.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
