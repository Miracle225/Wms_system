package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.models.Good;
import com.example.wms_system.services.GoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/goods")
public class GoodController {
    private final GoodService goodService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Good> getAllGoods(
            @RequestParam Optional<GoodStatus> status,
            @RequestParam Optional<GoodCategory> category,
            @RequestParam Optional<Long> warehouseId
    ) {
        if (status.isPresent())
            return goodService.getAllByStatus(status.get());
        else if (category.isPresent())
            return goodService.getAllByCategory(category.get());
        else if (warehouseId.isPresent())
            return goodService.getAllByWarehouse(warehouseId.get());

        return goodService.getAllGoods();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Good getById(@PathVariable Long id){
        return goodService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/name/{name}")
    public Good getByName(@PathVariable String name){
        return goodService.getByName(name);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/code/{code}")
    public Good getByCode(@PathVariable Long code){
        return goodService.getByCode(code);
    }

    @PostMapping()
    ResponseEntity<?> createGood(@RequestBody @Valid GoodDto goodDto){
        var good = goodService.createNewGood(goodDto);
        return new ResponseEntity<>(good,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateGood(@PathVariable Long id, @RequestBody @Valid GoodDto goodDto){
        var good = goodService.updateGood(id,goodDto);
        return new ResponseEntity<>(good,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteGood(@PathVariable Long id){
        goodService.deleteGoodById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

