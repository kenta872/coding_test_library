package com.company.library.primary.controller;

import com.company.library.primary.service.ReturnBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReturnBookControllerTest {
    @Mock
    private ReturnBookService returnBookService;
    @InjectMocks
    private ReturnBookController returnBookController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(returnBookController).build();
    }

    @Test
    public void returnBook() throws Exception {
        // setup
        final String requestBody = "{\"employeeId\":1,\"bookId\":1}";

        // do
        mockMvc.perform(MockMvcRequestBuilders.post("/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void returnAllBook() throws Exception {
        // setup
        final String requestBody = "{\"employeeId\":1,\"bookId\":1}";

        // do
        mockMvc.perform(MockMvcRequestBuilders.post("/return/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
