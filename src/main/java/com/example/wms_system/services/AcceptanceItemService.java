package com.example.wms_system.services;

import com.example.wms_system.repositories.AcceptanceItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcceptanceItemService {
    private final AcceptanceItemRepository acceptanceItemRepository;

}
