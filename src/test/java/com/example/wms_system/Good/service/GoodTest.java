package com.example.wms_system.Good.service;

import com.example.wms_system.Good.entity.GoodDtoData;
import com.example.wms_system.Good.entity.GoodEntityData;
import com.example.wms_system.dto.GoodDto;
import com.example.wms_system.enums.GoodCategory;
import com.example.wms_system.enums.GoodStatus;
import com.example.wms_system.exceptions.GoodNotFoundException;
import com.example.wms_system.models.Good;
import com.example.wms_system.repositories.GoodRepository;
import com.example.wms_system.services.GoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GoodTest {

    @Mock
    GoodRepository goodRepository;

    @InjectMocks
    GoodService goodService;

    @Test
    void should_return_all_goods() {
        List<Good> good = GoodEntityData.getListOfGood();
        when(goodRepository.findAll()).thenReturn(good);
        List<Good> expected = goodService.getAllGoods();

        assertEquals(expected, good);
        verify(goodRepository).findAll();
    }

    @Test
    void should_return_all_goods_by_category() {
        List<Good> goods = GoodEntityData.getListOfGood();
        when(goodRepository.findAllByCategory(GoodCategory.FOOD_AND_BEVERAGE)).thenReturn(goods);
        List<Good> expected = goodService.getAllByCategory(GoodCategory.FOOD_AND_BEVERAGE);

        assertEquals(expected, goods);
        verify(goodRepository).findAllByCategory(GoodCategory.FOOD_AND_BEVERAGE);
    }
    @Test
    void should_return_all_goods_by_status() {
        List<Good> goods = GoodEntityData.getListOfGood();
        when(goodRepository.findAllByStatus(GoodStatus.ON_STOCK)).thenReturn(goods);
        List<Good> expected = goodService.getAllByStatus(GoodStatus.ON_STOCK);

        assertEquals(expected, goods);
        verify(goodRepository).findAllByStatus(GoodStatus.ON_STOCK);
    }
    @Test
    void should_return_all_goods_by_warehouse() {
        List<Good> goods = GoodEntityData.getListOfGood();
        when(goodRepository.findAllByWarehouseId(1L)).thenReturn(goods);
        List<Good> expected = goodService.getAllByWarehouse(1L);

        assertEquals(expected, goods);
        verify(goodRepository).findAllByWarehouseId(1L);
    }

    @Test
    void when_given_id_should_return_good_if_found() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();

        //when
        when(goodRepository.findById(good.getId())).thenReturn(Optional.of(good));
        goodService.deleteGoodById(good.getId());

        //then
        verify(goodRepository).deleteById(good.getId());
    }

    @Test()
    void should_throw_exception_when_good_doesnt_exist() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();

        //when
        when(goodRepository.findById(anyLong())).thenReturn(Optional.empty());
        GoodNotFoundException exception = assertThrows(GoodNotFoundException.class, () ->
                goodService.getById(good.getId()));
        //then
        assertTrue(exception.getMessage().contains("Good with this id{ 1 } not found!"));
    }


    @Test
    void when_save_good_should_return_good() {

        //given
        GoodDto goodDto = GoodDtoData.getGood();
        Good good = GoodEntityData.getSingleGood();

        //when
        when(goodRepository.save(any(Good.class))).thenReturn(good);
        Good created = goodService.createNewGood(goodDto);

        //then
        assertThat(created.getName()).isSameAs(goodDto.getName());
    }


    @Test
    void when_given_id_should_update_good_if_found() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();
        GoodDto updatedGood = GoodDtoData.getUpdatedGood();
        Good good1 = updatedGood.toGoodEntity();
        good1.setId(1L);

        //when
        when(goodRepository.findById(good.getId())).thenReturn(Optional.of(good));
        goodService.updateGood(good.getId(), updatedGood);


        //then
        verify(goodRepository).findById(good.getId());
    }

    @Test()
    void should_throw_exception_when_update_good_doesnt_exist() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();
        GoodDto goodDto = GoodDtoData.getUpdatedGood();

        //when
        when(goodRepository.findById(anyLong())).thenReturn(Optional.empty());
        GoodNotFoundException exception = assertThrows(GoodNotFoundException.class, () ->
                goodService.updateGood(good.getId(), goodDto));

        //then
        assertTrue(exception.getMessage().contains("Good with this id{ 1 } not found!"));

    }

    //delete user
    @Test
    void when_given_id_should_delete_good_if_found() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();

        when(goodRepository.findById(good.getId())).thenReturn(Optional.of(good));
        goodService.deleteGoodById(good.getId());

        //then
        verify(goodRepository).deleteById(good.getId());
    }

    @Test()
    void should_throw_exception_when_delete_good_doesnt_exist() {
        //given
        Good good = GoodEntityData.getSingleGoodWithId();

        //when
        when(goodRepository.findById(anyLong())).thenReturn(Optional.empty());
        GoodNotFoundException exception = assertThrows(GoodNotFoundException.class, () ->
                goodService.deleteGoodById(good.getId()));

        //then
        assertTrue(exception.getMessage().contains("Good with this id{ 1 } not found!"));
    }
}
