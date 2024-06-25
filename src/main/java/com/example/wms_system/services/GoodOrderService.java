package com.example.wms_system.services;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.dto.GoodOrderDto;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.models.Good;
import com.example.wms_system.models.GoodOrder;
import com.example.wms_system.repositories.GoodOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GoodOrderService {

    private final GoodOrderRepository goodOrderRepository;

    public GoodOrder getById(Long id) {
        Optional<GoodOrder> loadGood = goodOrderRepository.findById(id);
        log.info("Loaded order goods with id: {}", id);
        return loadGood.orElseThrow(() -> {
            log.error("Order goods with id: {} not found", id);
            return new GoodNotFoundException(id);
        });
    }

    public List<GoodOrder> getAllGoodsByOrder(Long id){
        return printLogInfo(goodOrderRepository.findAllByOrderId(id));
    }
    @Transactional
    public GoodOrder createNewOrderGood(GoodOrderDto goodOrderDto) {
        GoodOrder goodOrder = goodOrderDto.toGoodOrderEntity();
        GoodOrder savedGoodOrder = goodOrderRepository.save(goodOrder);
        log.info("Saved good order with id: {}", savedGoodOrder.getId());
        return savedGoodOrder;
    }

    @Transactional
    public GoodOrder updateGoodOrder(Long id, GoodOrderDto goodDto) {
        GoodOrder good = goodOrderRepository.findById(id).orElseThrow(() -> new GoodNotFoundException(id));
        return updateGoodFields(goodDto, good);
    }

    public void deleteGoodOrderById(Long id) {
        if (goodOrderRepository.findById(id).isPresent()) {
            goodOrderRepository.deleteById(id);
            log.info("Delete order good by id: {}", id);
        }else {
            throw new GoodNotFoundException(id);
        }
    }

    private GoodOrder updateGoodFields(GoodOrderDto goodDto, GoodOrder good) {
        GoodOrder updatedGood = good.updateFields(goodDto);
        log.info("Updated order good with id: {}", updatedGood.getId());
        return updatedGood;
    }

    private List<GoodOrder> printLogInfo(List<GoodOrder> goods) {
        log.info("Size of loaded order goods from database: {}", goods.size());
        return goods;
    }

}
