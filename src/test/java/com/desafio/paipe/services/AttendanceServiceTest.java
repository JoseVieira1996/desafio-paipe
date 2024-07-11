package com.desafio.paipe.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.desafio.paipe.entities.AttendanceEntity;
import com.desafio.paipe.entities.enums.Flow;
import com.desafio.paipe.repositories.AttendanceRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AttendanceService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AttendanceServiceTest {
    @MockBean
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AttendanceService attendanceService;


    @Test
    void testSaveAttendance() {
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setDepartment("Department");
        attendanceEntity.setFlow(Flow.ENTRY);
        attendanceEntity.setId(1L);
        attendanceEntity.setName("Name");
        attendanceEntity.setTimestamp(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(attendanceRepository.save(Mockito.<AttendanceEntity>any())).thenReturn(attendanceEntity);

        AttendanceEntity actualSaveAttendanceResult = attendanceService.saveAttendance(null);

        verify(attendanceRepository).save(isNull());
        assertSame(attendanceEntity, actualSaveAttendanceResult);
    }


    @Test
    void testGetAllAttendances() {
        ArrayList<AttendanceEntity> attendanceEntityList = new ArrayList<>();
        when(attendanceRepository.findAll()).thenReturn(attendanceEntityList);

        List<AttendanceEntity> actualAllAttendances = attendanceService.getAllAttendances();

        verify(attendanceRepository).findAll();
        assertTrue(actualAllAttendances.isEmpty());
        assertSame(attendanceEntityList, actualAllAttendances);
    }
}
