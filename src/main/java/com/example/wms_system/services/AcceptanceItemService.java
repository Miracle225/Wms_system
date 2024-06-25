package com.example.wms_system.services;

import com.example.wms_system.dto.AcceptanceItemDto;
import com.example.wms_system.dto.OrderDto;
import com.example.wms_system.enums.OrderStatus;
import com.example.wms_system.enums.PaymentMethod;
import com.example.wms_system.exceptions.AcceptanceItemNotFoundException;
import com.example.wms_system.exceptions.OrderNotFoundException;
import com.example.wms_system.models.AcceptanceItem;
import com.example.wms_system.models.Customer;
import com.example.wms_system.models.Order;
import com.example.wms_system.repositories.AcceptanceItemRepository;
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
public class AcceptanceItemService {
    private final AcceptanceItemRepository acceptanceItemRepository;

    public AcceptanceItem getById(Long id) {
        Optional<AcceptanceItem> loadItem = acceptanceItemRepository.findById(id);
        log.info("Loaded acceptance item with id: {}", id);
        return loadItem.orElseThrow(() -> {
            log.error("Acceptance item with id: {} not found", id);
            return new AcceptanceItemNotFoundException(id);
        });
    }

    public List<AcceptanceItem> getAll(){
        return printLogInfo(acceptanceItemRepository.findAll());
    }

    public List<AcceptanceItem> getAllByAcceptanceId(Long id){
        return printLogInfo(acceptanceItemRepository.findAllByAcceptanceId(id));
    }

    public AcceptanceItem getByAcceptanceIdAndGoodId(Long acceptId, Long goodId){

        return acceptanceItemRepository.findByAcceptanceIdAndGoodId(acceptId,goodId).orElseThrow(()-> new AcceptanceItemNotFoundException("Acceptance item with acceptanceId: {} and goodId: {} can`t be found",acceptId,goodId));
    }
    @Transactional
    public AcceptanceItem createNewAcceptanceItem(AcceptanceItemDto itemDto) {
        AcceptanceItem item = itemDto.toAcceptanceItemEntity();
        AcceptanceItem savedItem = acceptanceItemRepository.save(item);
        log.info("Saved accepted item with id: {}", savedItem.getId());
        return savedItem;
    }

    @Transactional
    public AcceptanceItem updateAcceptanceItem(Long id, AcceptanceItemDto itemDto) {
        AcceptanceItem item = acceptanceItemRepository.findById(id).orElseThrow(() -> new AcceptanceItemNotFoundException(id));
        return updateAcceptanceItemFields(item,itemDto);
    }
    public void deleteAcceptanceItemById(Long id) {
        if(getById(id)!=null) {
            acceptanceItemRepository.deleteById(id);
            log.info("Delete acceptance item by id: {}", id);
        }else {
            throw new AcceptanceItemNotFoundException(id);
        }
    }

    private AcceptanceItem updateAcceptanceItemFields(AcceptanceItem item, AcceptanceItemDto itemDto) {
        AcceptanceItem updatedItem = item.updateFields(itemDto);
        log.info("Updated accepted item with id: {}", updatedItem.getId());
        return updatedItem;
    }
    private List<AcceptanceItem> printLogInfo(List<AcceptanceItem> items) {
        log.info("Size of loaded acceptance items from database: {}", items.size());
        return items;
    }

}
