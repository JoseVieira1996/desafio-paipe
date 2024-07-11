package com.desafio.paipe.resources;

import static org.mockito.Mockito.when;

import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.entities.AttendanceEntity;
import com.desafio.paipe.entities.enums.Flow;
import com.desafio.paipe.services.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AttendanceResource.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AttendanceResourceTest {
    @Autowired
    private AttendanceResource attendanceResource;

    @MockBean
    private AttendanceService attendanceService;


    @Test
    void testSaveAttendance() throws Exception {
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setDepartment("Department");
        attendanceEntity.setFlow(Flow.ENTRY);
        attendanceEntity.setId(1L);
        attendanceEntity.setName("Name");
        attendanceEntity.setTimestamp(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(attendanceService.saveAttendance(Mockito.<RequestAttendanceDTO>any())).thenReturn(attendanceEntity);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/attendances")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new RequestAttendanceDTO("Name", "Flow", "Department")));

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(attendanceResource)
                .build()
                .perform(requestBuilder);

        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"flow\":\"ENTRY\",\"department\":\"Department\",\"timestamp\":\"1970-01-01T00:00Z\"}"));
    }


    @Test
    void testGetAllAttendances() throws Exception {
        when(attendanceService.getAllAttendances()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/attendances");

        MockMvcBuilders.standaloneSetup(attendanceResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
