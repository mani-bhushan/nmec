package com.apps.nmec.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public class BaseEntity extends Auditable<String> implements Serializable{

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    protected LocalDateTime endDate;

    @Column(name = "is_active", nullable = false)
    protected Boolean isActive;
}


