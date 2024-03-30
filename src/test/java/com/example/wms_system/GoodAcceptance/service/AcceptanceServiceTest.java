package com.example.wms_system.GoodAcceptance.service;

import com.example.wms_system.GoodAcceptance.entity.AcceptanceDtoData;
import com.example.wms_system.GoodAcceptance.entity.AcceptanceEntityData;
import com.example.wms_system.dto.GoodAcceptanceDto;
import com.example.wms_system.exceptions.AcceptanceNotFoundException;
import com.example.wms_system.models.GoodAcceptance;
import com.example.wms_system.repositories.GoodAcceptanceRepository;
import com.example.wms_system.services.GoodAcceptanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AcceptanceServiceTest {

    @Mock
    GoodAcceptanceRepository acceptanceRepository;

    @InjectMocks
    GoodAcceptanceService acceptanceService;

    @Test
    void should_return_all_acceptances() {
        List<GoodAcceptance> goodAcceptances = AcceptanceEntityData.getListOfGoodAcceptance();
        when(acceptanceRepository.findAll()).thenReturn(goodAcceptances);
        List<GoodAcceptance> expected = acceptanceService.getAll();

        assertEquals(expected, goodAcceptances);
        verify(acceptanceRepository).findAll();
    }

    @Test
    void should_return_all_acceptances_by_date() {
        List<GoodAcceptance> goodAcceptances = AcceptanceEntityData.getListOfGoodAcceptance();
        when(acceptanceRepository.findAllByAcceptionDate(Date.valueOf(LocalDate.now()))).thenReturn(goodAcceptances);
        List<GoodAcceptance> expected = acceptanceService.getAllByAcceptanceDate(Date.valueOf(LocalDate.now()));

        assertEquals(expected, goodAcceptances);
        verify(acceptanceRepository).findAllByAcceptionDate(Date.valueOf(LocalDate.now()));
    }

    @Test
    void when_given_id_should_return_acceptance_if_found() {
        //given
        GoodAcceptance goodAcceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();

//when
        when(acceptanceRepository.findById(goodAcceptance.getId())).thenReturn(Optional.of(goodAcceptance));
        acceptanceService.deleteAcceptanceById(goodAcceptance.getId());

        //then
        verify(acceptanceRepository).deleteById(goodAcceptance.getId());
    }

    @Test()
    void should_throw_exception_when_acceptance_doesnt_exist() {
        //given
        GoodAcceptance acceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();

        //when
        when(acceptanceRepository.findById(anyLong())).thenReturn(Optional.empty());
        AcceptanceNotFoundException exception = assertThrows(AcceptanceNotFoundException.class, () ->
                acceptanceService.getById(acceptance.getId()));
        //then
        assertTrue(exception.getMessage().contains("Good acceptance with this id{ 1 } not found!"));
    }

    //create status
    @Test
    void when_save_acceptance_should_return_acceptance() {

        //given
        GoodAcceptanceDto goodAcceptanceDto = AcceptanceDtoData.getGoodAcceptance();
        GoodAcceptance goodAcceptance = AcceptanceEntityData.getSingleGoodAcceptance();

        //when
        when(acceptanceRepository.save(any(GoodAcceptance.class))).thenReturn(goodAcceptance);
        GoodAcceptance created = acceptanceService.createNewAcceptance(goodAcceptanceDto);

        //then
        assertThat(created.getQuantity()).isSameAs(goodAcceptanceDto.getQuantity());
    }

    //update user
    @Test
    void when_given_id_should_update_acceptance_if_found() {
        //given
        GoodAcceptance acceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();
        GoodAcceptanceDto updatedAcceptance = AcceptanceDtoData.getUpdatedGoodAcceptance();
        GoodAcceptance acceptance1 = updatedAcceptance.toAcceptanceEntity();
        acceptance1.setId(1L);

        //when
        when(acceptanceRepository.findById(acceptance.getId())).thenReturn(Optional.of(acceptance));
        acceptanceService.updateAcceptance(acceptance.getId(), updatedAcceptance);


        //then
        verify(acceptanceRepository).findById(acceptance.getId());
    }

    @Test()
    void should_throw_exception_when_update_acceptance_doesnt_exist() {
        //given
        GoodAcceptance goodAcceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();
        GoodAcceptanceDto acceptanceDto = AcceptanceDtoData.getUpdatedGoodAcceptance();

        //when
        when(acceptanceRepository.findById(anyLong())).thenReturn(Optional.empty());
        AcceptanceNotFoundException exception = assertThrows(AcceptanceNotFoundException.class, () ->
                acceptanceService.updateAcceptance(goodAcceptance.getId(), acceptanceDto));

        //then
        assertTrue(exception.getMessage().contains("Good acceptance with this id{ 1 } not found!"));

    }

    //delete user
    @Test
    void when_given_id_should_delete_acceptance_if_found() {
        //given
        GoodAcceptance goodAcceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();

        when(acceptanceRepository.findById(goodAcceptance.getId())).thenReturn(Optional.of(goodAcceptance));
        acceptanceService.deleteAcceptanceById(goodAcceptance.getId());

        //then
        verify(acceptanceRepository).deleteById(goodAcceptance.getId());
    }

    @Test()
    void should_throw_exception_when_delete_acceptance_doesnt_exist() {
        //given
        GoodAcceptance acceptance = AcceptanceEntityData.getSingleGoodAcceptanceWithId();

        //when
        when(acceptanceRepository.findById(anyLong())).thenReturn(Optional.empty());
        AcceptanceNotFoundException exception = assertThrows(AcceptanceNotFoundException.class, () ->
                acceptanceService.deleteAcceptanceById(acceptance.getId()));

        //then
        assertTrue(exception.getMessage().contains("Good acceptance with this id{ 1 } not found!"));
    }
}
