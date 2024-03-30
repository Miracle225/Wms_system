package com.example.wms_system.controllers;

import com.example.wms_system.dto.OperationDto;
import com.example.wms_system.enums.OperationType;
import com.example.wms_system.models.Operation;
import com.example.wms_system.services.OperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/operations")
public class OperationController {
    private final OperationService operationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Operation> getAllOperations(
            @RequestParam Optional<OperationType> type,
            @RequestParam Optional<Date> opDate,
            @RequestParam Optional<Date> start,
            @RequestParam Optional<Date> end
    ) {
        if (type.isPresent())
            return operationService.getAllByOperationType(type.get());
        else if (opDate.isPresent())
            return operationService.getAllByOperationDate(opDate.get());
        else if (start.isPresent() && end.isPresent()) {
            return operationService.getAllByOperationDatePeriod(start.get(), end.get());
        }
        return operationService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Operation getById(@PathVariable Long id){
        return operationService.getById(id);
    }

    @PostMapping()
    ResponseEntity<?> createOperation(@RequestBody @Valid OperationDto operationDto){
        var operation = operationService.createNewOperation(operationDto);
        return new ResponseEntity<>(operation,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateOperation(@PathVariable Long id, @RequestBody @Valid OperationDto operationDto){
        var operation = operationService.updateOperation(id,operationDto);
        return new ResponseEntity<>(operation,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOperation(@PathVariable Long id){
        operationService.deleteOperationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
