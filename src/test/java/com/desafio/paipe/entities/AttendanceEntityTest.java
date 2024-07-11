package com.desafio.paipe.entities;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AttendanceEntity.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AttendanceEntityTest {
    @Autowired
    private AttendanceEntity attendanceEntity;


    @Test
    void testPrePersist() {
        TemporalAdjuster temporalAdjuster = mock(TemporalAdjuster.class);
        when(temporalAdjuster.adjustInto(Mockito.<Temporal>any()))
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        OffsetDateTime timestamp = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        timestamp.with(temporalAdjuster);

        AttendanceEntity attendanceEntity2 = new AttendanceEntity();
        attendanceEntity2.setTimestamp(timestamp);
        attendanceEntity2.prePersist();
        verify(temporalAdjuster).adjustInto(isA(Temporal.class));
    }
}
