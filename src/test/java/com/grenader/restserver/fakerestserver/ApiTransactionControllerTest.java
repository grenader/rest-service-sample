package com.grenader.restserver.fakerestserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grenader.restserver.fakerestserver.model.ObjectResponse;
import com.grenader.restserver.fakerestserver.model.WorkHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetVersion() throws Exception {
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/version")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version", is(0.1)))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content = " + content);
    }

    @Test
    public void testCreateTransaction() throws Exception {
        Random random = new Random();
        final int expectedUserId = random.nextInt(10);
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON).content("{\"userId\": " + expectedUserId + "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(expectedUserId)))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content = " + content);
    }


    @Test
    public void testCreateObject() throws Exception {
        final int expectedUserId = new Random().nextInt(20);
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/object")
                .contentType(MediaType.APPLICATION_JSON).content("{\"userId\": " + expectedUserId + "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(expectedUserId)))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("response content = " + content);

        ObjectResponse data = objectMapper.readValue(content, ObjectResponse.class);
        System.out.println("response object = " + data);
        assertEquals(expectedUserId+1, data.getSeriesId());
        assertEquals("unknown address", data.getAddress());

        assertNotNull(data.getWorkHistory());
        assertEquals(1, data.getWorkHistory().size());
        assertEquals(new WorkHistory("Company One",
                LocalDate.of(2010, 01, 01),
                LocalDate.of(2012, 06, 01),"manager"), data.getWorkHistory().get(0));

    }
}
