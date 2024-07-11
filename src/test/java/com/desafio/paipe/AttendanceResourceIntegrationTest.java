package com.desafio.paipe;

import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.entities.AttendanceEntity;
import com.desafio.paipe.entities.enums.Flow;
import com.desafio.paipe.repositories.AttendanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        attendanceRepository.deleteAll();
    }

    @Test
    void testSaveAttendance() throws Exception {
        RequestAttendanceDTO request = new RequestAttendanceDTO("John Doe", "ENTRY", "Sales");

        mockMvc.perform(post("/api/attendances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.flow").value("ENTRY"))
                .andExpect(jsonPath("$.department").value("Sales"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testGetAllAttendances() throws Exception {
        AttendanceEntity attendance1 = new AttendanceEntity();
        attendance1.setName("John Doe");
        attendance1.setFlow(Flow.ENTRY);
        attendance1.setDepartment("Sales");

        AttendanceEntity attendance2 = new AttendanceEntity();
        attendance2.setName("Jane Smith");
        attendance2.setFlow(Flow.EXIT);
        attendance2.setDepartment("Marketing");

        attendanceRepository.saveAll(List.of(attendance1, attendance2));

        mockMvc.perform(get("/api/attendances"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].flow").value("ENTRY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].department").value("Sales"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].flow").value("EXIT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].department").value("Marketing"));
    }
}
