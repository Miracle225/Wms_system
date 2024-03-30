package com.example.wms_system.Good.entity.controller;

import com.example.wms_system.Auth.entity.SignUpRequestData;
import com.example.wms_system.Security.JWTAuthenticationFilter;
import com.example.wms_system.controllers.GoodController;
import com.example.wms_system.payload.SignUpRequest;
import com.example.wms_system.services.GoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

/*@ExtendWith(MockitoExtension.class)
@WebMvcTest(GoodController.class)
public class GoodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GoodService goodService;

    @MockBean
    JWTAuthenticationFilter authenticationFilter;

    @Test
    void find_all_options_by_status() throws Exception {
        SignUpRequest signUpRequest = SignUpRequestData.getSingleSignUpRequest();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8008/auth/sign-up")
                        .content(toJsonString(signUpRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void find_all_options_by_key() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/options/all")
                        .queryParam("key", "key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void find_all_options_by_visible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/options/all")
                        .queryParam("visible", "true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void find_all_options_by_invisible() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/options/all")
                        .queryParam("visible", "false")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void find_all_options_and_return_status_ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/options/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
*/
    /*
        @Test
        void create_option_and_return_status_created() throws Exception {
            //given
            OptionDto option = OptionDtoData.getSingleOptionDto();

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders
                            .post("http://localhost:8080/options")
                            .content(toJsonString(option))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        void update_option_and_return_status_accepted() throws Exception {
            //given
            OptionDto option = OptionDtoData.getUpdatedOptionDto();

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders
                            .put("http://localhost:8080/options/1")
                            .content(toJsonString(option))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isAccepted());
        }

        @Test
        void update_option_visibility_and_return_status_accepted() throws Exception {
            //given
            Map<String, String> body = new HashMap<>();
            body.put("visible", "true");

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders
                            .patch("http://localhost:8080/options/1/visible")
                            .content(toJsonString(body))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isAccepted());
        }

        @Test
        void when_accepted_wrong_value_then_return_status_bad_request() throws Exception {
            //given
            Map<String, String> body = new HashMap<>();
            body.put("visible", "true");

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders
                            .put("http://localhost:8080/options/1")
                            .content(toJsonString(body))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        void deleted_option_and_return_status_no_content() throws Exception {
            //given
            OptionDto option = OptionDtoData.getUpdatedOptionDto();

            //when
            //then
            mockMvc.perform(MockMvcRequestBuilders
                            .delete("http://localhost:8080/options/1")
                            .content(toJsonString(option))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());
        }

    private static String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}*/
