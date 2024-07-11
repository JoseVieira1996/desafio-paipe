package com.desafio.paipe.resources;

import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.dto.ResponseAttendanceDTO;
import com.desafio.paipe.entities.AttendanceEntity;
import com.desafio.paipe.services.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.desafio.paipe.mapper.AttendenceMapper.INSTANCE_MAPPER;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceResource {

    private final AttendanceService attendanceService;

    public AttendanceResource(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<ResponseAttendanceDTO> saveAttendance(@RequestBody RequestAttendanceDTO attendanceDTO) {
        var savedAttendance = attendanceService.saveAttendance(attendanceDTO);
        var response = INSTANCE_MAPPER.convertToResponse(savedAttendance);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAttendanceDTO>> getAllAttendances() {
        List<AttendanceEntity> attendances = attendanceService.getAllAttendances();
        var responseList = INSTANCE_MAPPER.convertToList(attendances);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}

