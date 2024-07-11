package com.example.flashcardquiz;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private static QuestionBank instance;
    private List<Questions> questions;

    // Private constructor to prevent instantiation
    private QuestionBank() {
        questions = new ArrayList<>();
    }

    // Singleton instance retrieval
    public static synchronized QuestionBank getInstance() {
        if (instance == null) {
            instance = new QuestionBank();
        }
        return instance;
    }

    // Method to add a question
    public void addQuestion(Questions question) {
        questions.add(question);
    }

    // Method to get all questions
    public List<Questions> getQuestions() {
        return questions;
    }
}
