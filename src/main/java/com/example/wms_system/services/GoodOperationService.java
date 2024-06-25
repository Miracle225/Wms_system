package com.example.wms_system.services;

import com.example.wms_system.dto.GoodOperationDto;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.exceptions.OperationNotFoundException;
import com.example.wms_system.models.GoodOperation;
import com.example.wms_system.repositories.GoodOperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GoodOperationService {

    private final GoodOperationRepository goodOperationRepository;

    public GoodOperation getById(Long id) {
        Optional<GoodOperation> loadGood = goodOperationRepository.findById(id);
        log.info("Loaded operation goods with id: {}", id);
        return loadGood.orElseThrow(() -> {
            log.error("Operation goods with id: {} not found", id);
            return new OperationNotFoundException(id);
        });
    }

    public List<GoodOperation> getAllGoodsByOperation(Long id){
        return printLogInfo(goodOperationRepository.findAllByOperationId(id));
    }
    @Transactional
    public GoodOperation createNewOperationGood(GoodOperationDto operationDto) {
        GoodOperation goodOperation = operationDto.toGoodOperationEntity();
       GoodOperation savedGoodOrder = goodOperationRepository.save(goodOperation);
        log.info("Saved good operation with id: {}", savedGoodOrder.getId());
        return savedGoodOrder;
    }

    @Transactional
    public GoodOperation updateGoodOperation(Long id, GoodOperationDto operationDto) {
        GoodOperation goodOperation = goodOperationRepository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));
        return updateOperationFields(goodOperation,operationDto);
    }

    public void deleteGoodOperationById(Long id) {
        if (goodOperationRepository.findById(id).isPresent()) {
            goodOperationRepository.deleteById(id);
            log.info("Delete operation good by id: {}", id);
        }else {
            throw new GoodNotFoundException(id);
        }
    }

    private GoodOperation updateOperationFields(GoodOperation goodOperation, GoodOperationDto operationDto) {
        GoodOperation updatedOperation = goodOperation.updateFields(operationDto);
        log.info("Updated operation good with id: {}", updatedOperation.getId());
        return updatedOperation;
    }

    private List<GoodOperation> printLogInfo(List<GoodOperation> goods) {
        log.info("Size of loaded operation goods from database: {}", goods.size());
        return goods;
    }

}
