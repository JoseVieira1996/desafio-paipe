package com.desafio.paipe.repositories;

import com.desafio.paipe.entities.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
}