package com.example.wms_system.services;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.exceptions.GoodAlreadyExists;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.models.Good;
import com.example.wms_system.repositories.GoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GoodService {
    private final GoodRepository goodRepository;

    public Good getById(Long id) {
        Optional<Good> loadGood = goodRepository.findById(id);
        log.info("Loaded good with id: {}", id);
        return loadGood.orElseThrow(() -> {
            log.error("Good with id: {} not found", id);
            return new GoodNotFoundException(id);
        });
    }


    public Good getByCode(Long code) {
        Optional<Good> loadGood = goodRepository.findByCode(code);
        log.info("Loaded good with code: {}", code);
        return loadGood.orElseThrow(() -> {
            log.error("Good with code: {} not found", code);
            return new GoodNotFoundException("Good with code: { " + code + " }not found!");
        });

    }

    public Good getByName(String name) {
        Optional<Good> loadGood = goodRepository.findByName(name);
        log.info("Loaded good with name: {}", name);
        return loadGood.orElseThrow(() -> {
            log.error("Good with name: {} not found", name);
            return new GoodNotFoundException("Good with name: { " + name + " }not found!");
        });
    }

    public List<Good> getAllByCategory(GoodCategory category) {
        return printLogInfo(goodRepository.findAllByCategory(category));
    }
    public  List<Good> getAllByNamePart(String name){
        return  printLogInfo(goodRepository.findAllByName(name));
    }
    public  List<Good> getAllById(Long id){
        return  printLogInfo(List.of(goodRepository.findById(id).get()));
    }
    public List<Good> getAllByStatus(GoodStatus status) {
        return printLogInfo(goodRepository.findAllByStatus(status));
    }

    public List<Good> getAllByWarehouse(Long id) {
        return printLogInfo(goodRepository.findAllByWarehouseId(id));
    }
    public List<Good> getAllGoods(){
        return printLogInfo(goodRepository.findAll());
    }

    @Transactional
    public Good createNewGood(GoodDto goodDto) {
        checkIfCodeExists(goodDto.getCode());
        Good good = goodDto.toGoodEntity();
        Good savedGood = goodRepository.save(good);
        log.info("Saved good with id: {}", savedGood.getId());
        return savedGood;
    }

    @Transactional
    public Good updateGood(Long id, GoodDto goodDto) {
        Good good = goodRepository.findById(id).orElseThrow(() -> new GoodNotFoundException(id));
        // checkIfCodeExists(goodDto.getCode());
        return updateGoodFields(goodDto, good);
    }

    public void deleteGoodById(Long id) {
        if (goodRepository.findById(id).isPresent()) {
            goodRepository.deleteById(id);
            log.info("Delete good by id: {}", id);
        }else {
            throw new GoodNotFoundException(id);
        }
    }

    private void checkIfCodeExists(Long code) {
        if (goodRepository.findByCode(code).isPresent()) {
            log.error("Good with this code: {} already exists", code);
            throw new GoodAlreadyExists(code);
        }
    }

    private Good updateGoodFields(GoodDto goodDto, Good good) {
        Good updatedGood = good.updateFields(goodDto);
        log.info("Updated good with id: {}", updatedGood.getId());
        return updatedGood;
    }

    private List<Good> printLogInfo(List<Good> goods) {
        log.info("Size of loaded goods from database: {}", goods.size());
        return goods;
    }

}
