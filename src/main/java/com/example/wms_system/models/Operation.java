package com.example.wms_system.models;

import com.example.wms_system.dto.OperationDto;
import com.example.wms_system.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "operations")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {
    /*
 CREATE TABLE Operation
(
id                    INT  NOT NULL ,
operation_type        VARCHAR2(50)  NOT NULL ,
operation_date        DATE  NOT NULL ,
operation_description  VARCHAR2(255)  NOT NULL ,
quantity              INTEGER  NOT NULL ,
user_id               INT  NOT NULL
);

  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "operation_date",columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    private Date operationDate;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Operation(OperationType operationType,
                     String description,
                     User user) {
        this.operationType = operationType;
        this.operationDate = new Date(System.currentTimeMillis());
        this.description = description;
        this.user = user;
    }

    public Operation updateFields(OperationDto operationDto){
        if(operationDto.getDescription()!=null){
            description = operationDto.getDescription();
        }
        if(operationDto.getType()!=null){
            operationType = operationDto.getType();
        }
        if(operationDto.getUser()!=null){
            user = operationDto.getUser();
        }
        return this;
    }
}
