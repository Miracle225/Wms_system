package com.example.wms_system.controllers;

import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.services.GoodAcceptanceService;
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
@RequestMapping(value = "/reception")
public class AcceptanceController {
    private final GoodAcceptanceService acceptanceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<GoodAcceptance> getAllAcceptances(
            @RequestParam Optional<Date> date,
            @RequestParam Optional<Date> start,
            @RequestParam Optional<Date> end
    ) {
        if (date.isPresent())
            return acceptanceService.getAllByAcceptanceDate(date.get());
        else if (start.isPresent() && end.isPresent())
            return acceptanceService.getAllByDatePeriod(start.get(), end.get());
        return acceptanceService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public GoodAcceptance getById(@PathVariable Long id){
        return acceptanceService.getById(id);
    }

    @PostMapping()
    ResponseEntity<?> createAcceptance(@RequestBody @Valid GoodAcceptanceDto acceptanceDto){
        var acceptance = acceptanceService.createNewAcceptance(acceptanceDto);
        return new ResponseEntity<>(acceptance,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateAcceptance(@PathVariable Long id, @RequestBody @Valid GoodAcceptanceDto acceptanceDto){
        var acceptance = acceptanceService.updateAcceptance(id,acceptanceDto);
        return new ResponseEntity<>(acceptance,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAcceptance(@PathVariable Long id){
        acceptanceService.deleteAcceptanceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
