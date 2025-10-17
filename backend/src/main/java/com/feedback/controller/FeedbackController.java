package com.feedback.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.feedback.model.Feedback;
import com.feedback.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    @PostMapping
    public ResponseEntity<?> submitFeedback(@Valid @RequestBody Feedback feedback, BindingResult result) {
        if (result.hasErrors()) {
            log.warn("Validation failed: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            Feedback saved = service.saveFeedback(feedback);
            log.info("Feedback saved with id={}", saved.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            log.error("Error while saving feedback: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not submit feedback messages, please try again later.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getFeedback() {
        log.info("Retrieving all feedback messages");
        try {
            List<Feedback> feedbackList = service.getAllFeedback();
            log.info("Retrieved {} feedback entries", feedbackList.size());
            return ResponseEntity.ok(feedbackList);
        } catch (Exception e) {
            log.error("Error retrieving feedback messages: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not retrieve feedback messages, please try again later.");
        }
    }
}
