package com.desafio.paipe.mapper;

import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.dto.ResponseAttendanceDTO;
import com.desafio.paipe.dto.RequestAttendanceDTO;
import com.desafio.paipe.entities.AttendanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttendenceMapper {

    AttendenceMapper INSTANCE_MAPPER = Mappers.getMapper(AttendenceMapper.class);


    AttendanceEntity convertToEntity (RequestAttendanceDTO request);

    @Mapping(target="timestamp", expression = "java(entity.getTimestamp().toString())")
    ResponseAttendanceDTO convertToResponse (AttendanceEntity entity);


    List<ResponseAttendanceDTO> convertToList (List<AttendanceEntity> entity);


}
