package com.desafio.paipe.entities;

import com.desafio.paipe.entities.enums.Flow;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
@Entity(name="attenddance")
@ToString
@Getter
@Setter
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @NotNull
    private Flow flow;

    private String department;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime timestamp;

    @PrePersist
    public void prePersist() {
        this.timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    }
}