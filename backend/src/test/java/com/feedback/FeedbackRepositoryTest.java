package com.feedback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.feedback.model.Feedback;
import com.feedback.repository.FeedbackRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepository repository;

    @Test
    void shouldFindByEmail() {
        Feedback feedback = new Feedback("John Doe", "john@example.com", "Your platform looks great!");
        repository.save(feedback);

        List<Feedback> results = repository.findByEmail("john@example.com");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getMessage()).isEqualTo("Your platform looks great!");
    }
}
