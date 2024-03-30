package com.example.wms_system.controllers;

import com.example.wms_system.dto.ProviderDto;
import com.example.wms_system.models.Provider;
import com.example.wms_system.services.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/providers")
public class ProviderController {
    private final ProviderService providerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Provider> getAllOrders() {
        return providerService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Provider getById(@PathVariable Long id) {
        return providerService.getById(id);
    }

    @PostMapping()
    ResponseEntity<?> createOrder(@RequestBody @Valid ProviderDto providerDto) {
        var provider = providerService.createNewProvider(providerDto);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid ProviderDto providerDto) {
        var provider = providerService.updateProvider(id, providerDto);
        return new ResponseEntity<>(provider, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        providerService.deleteProviderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
