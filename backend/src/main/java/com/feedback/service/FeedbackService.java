package com.feedback.service;

import org.springframework.stereotype.Service;
import com.feedback.model.Feedback;
import com.feedback.repository.FeedbackRepository;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return repository.findAll();
    }

    public List<Feedback> getFeedbackByEmail(String email) {
        return repository.findByEmail(email);
    }
}
