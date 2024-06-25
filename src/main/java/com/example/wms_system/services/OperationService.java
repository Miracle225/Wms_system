package com.example.wms_system.services;

import com.example.wms_system.dto.OperationDto;
import com.example.wms_system.enums.OperationType;
import com.example.wms_system.exceptions.OperationNotFoundException;
import com.example.wms_system.models.Operation;
import com.example.wms_system.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationService {
    private final OperationRepository operationRepository;

    public Operation getById(Long id) {
        Optional<Operation> loadOperation = operationRepository.findById(id);
        log.info("Loaded operation with id: {}", id);
        return loadOperation.orElseThrow(() -> {
            log.error("Operation with id: {} not found", id);
            return new OperationNotFoundException(id);
        });
    }

    public List<Operation> getByIdToList(Long id) {
        return printLogInfo(List.of(operationRepository.findById(id).get()));
    }

    public List<Operation> getAllByOperationDate(Date date) {
        return printLogInfo(operationRepository.findAllByOperationDate(date));
    }

    public List<Operation> getAllByOperationType(OperationType type) {
        return printLogInfo(operationRepository.findAllByOperationType(type));
    }

    public List<Operation> getAllByOperationDatePeriod(Date start, Date end) {
        return printLogInfo(operationRepository.findAllInPeriod(start, end));
    }

    public List<Operation> getAllByUserId(Long id) {
        return printLogInfo(operationRepository.findAllByUserId(id));
    }

    public List<Operation> getAll() {
        return printLogInfo(operationRepository.findAll());
    }

    @Transactional
    public Operation createNewOperation(OperationDto operationDto) {
        Operation operation = operationDto.toOperationEntity();
        Operation savedOperation = operationRepository.save(operation);
        log.info("Saved operation with id: {}", savedOperation.getId());
        return savedOperation;
    }

    @Transactional
    public Operation updateOperation(Long id, OperationDto operationDto) {
        Operation operation = operationRepository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));
        return updateOperationFields(operation, operationDto);
    }

    public void deleteOperationById(Long id) {
        operationRepository.deleteById(id);
        log.info("Delete operation by id: {}", id);
    }

    private Operation updateOperationFields(Operation operation, OperationDto operationDto) {
        Operation updatedOperation = operation.updateFields(operationDto);
        log.info("Updated operation with id: {}", updatedOperation.getId());
        return updatedOperation;
    }

    private List<Operation> printLogInfo(List<Operation> operations) {
        log.info("Size of loaded operations from database: {}", operations.size());
        return operations;
    }
}
