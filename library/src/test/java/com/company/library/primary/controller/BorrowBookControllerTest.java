package com.company.library.primary.controller;

import com.company.library.primary.service.BorrowBookService;
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
public class BorrowBookControllerTest {
    @Mock
    private BorrowBookService borrowBookService;
    @InjectMocks
    private BorrowBookController borrowBookController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(borrowBookController).build();
    }

    @Test
    public void borrowBook() throws Exception {
        // setup
        final String requestBody = "{\"employeeId\":1,\"bookIdList\":[1,2,3]}";

        // do
        mockMvc.perform(MockMvcRequestBuilders.post("/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
