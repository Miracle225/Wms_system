package com.example.wms_system.services;

import com.example.wms_system.dto.ProviderDto;
import com.example.wms_system.exceptions.ProviderNotFoundException;
import com.example.wms_system.models.Provider;
import com.example.wms_system.repositories.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Provider getById(Long id) {
        Optional<Provider> loadProvider = providerRepository.findById(id);
        log.info("Loaded provider with id: {}", id);
        return loadProvider.orElseThrow(() -> {
            log.error("Provider with id: {} not found", id);
            return new ProviderNotFoundException(id);
        });
    }

    public List<Provider> getAll(){
        return printLogInfo(providerRepository.findAll());
    }

    @Transactional
    public Provider createNewProvider(ProviderDto providerDto) {
       Provider provider = providerDto.toProviderEntity();
        Provider savedProvider = providerRepository.save(provider);
        log.info("Saved provider with id: {}", savedProvider.getId());
        return savedProvider;
    }

    @Transactional
    public Provider updateProvider(Long id,ProviderDto providerDto) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException(id));
        return updateProviderFields(provider,providerDto);
    }
    public void deleteProviderById(Long id) {
        providerRepository.deleteById(id);
        log.info("Delete provider by id: {}", id);
    }

    private Provider updateProviderFields(Provider provider,ProviderDto providerDto) {
        Provider updatedProvider = provider.updateFields(providerDto);
        log.info("Updated provider with id: {}", updatedProvider.getId());
        return updatedProvider;
    }
    private List<Provider> printLogInfo(List<Provider> providers) {
        log.info("Size of loaded providers from database: {}", providers.size());
        return providers;
    }
}
