package com.feedback;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.feedback.model.Feedback;
import com.feedback.repository.FeedbackRepository;
import com.feedback.service.FeedbackService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    private final FeedbackRepository repository = Mockito.mock(FeedbackRepository.class);
    private final FeedbackService service = new FeedbackService(repository);

    @Test
    void shouldSaveFeedback() {
        Feedback feedback = new Feedback("John Doe", "john@example.com", "Your platform looks great!");
        when(repository.save(feedback)).thenReturn(new Feedback("John Doe", "john@example.com", "Your platform looks great!"));
        Feedback saved = service.saveFeedback(feedback);

        assertThat(saved.getName()).isEqualTo("John Doe");
        verify(repository, times(1)).save(feedback);
    }

    @Test
    void shouldReturnAllFeedback() {
        when(repository.findAll()).thenReturn(List.of(new Feedback("John Doe", "john@example.com", "Your platform looks great!")));

        List<Feedback> result = service.getAllFeedback();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("John Doe");
    }
}
