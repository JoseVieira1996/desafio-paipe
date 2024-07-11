package com.desafio.paipe.services;

import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.entities.AttendanceEntity;
import com.desafio.paipe.repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.desafio.paipe.mapper.AttendenceMapper.INSTANCE_MAPPER;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public AttendanceEntity saveAttendance(RequestAttendanceDTO attendanceDTO) {
        var attendance = INSTANCE_MAPPER.convertToEntity(attendanceDTO);
        return attendanceRepository.save(attendance);
    }

    public List<AttendanceEntity> getAllAttendances() {
        return attendanceRepository.findAll();
    }
}