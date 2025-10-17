package com.feedback;

import com.feedback.model.Feedback;
import com.feedback.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FeedbackRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldSubmitFeedbackSuccessfully() throws Exception {
        String json = """
            {
              "name": "John Doe",
              "email": "john@example.com",
              "message": "Your platform looks great!"
            }
            """;

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.message").value("Your platform looks great!"));
    }

    @Test
    void shouldFailValidationWhenMessageIsEmpty() throws Exception {
        String json = """
            {
              "name": "John Doe",
              "email": "john@example.com",
              "message": ""
            }
            """;

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailValidationWhenEmailIsEmpty() throws Exception {
        String json = """
            {
              "name": "John Doe",
              "email": "",
              "message": "Your platform looks great!"
            }
            """;

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRetrieveAllFeedback() throws Exception {
        repository.save(new Feedback("John Doe", "john@example.com", "Your platform looks great!"));

        mockMvc.perform(get("/api/feedback"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void shouldRetrieveFeedbackByEmail() throws Exception {
        repository.save(new Feedback("John Doe", "john@example.com", "Your platform looks great!"));

        mockMvc.perform(get("/api/feedback/email")
                        .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("Your platform looks great!"));
    }
}
