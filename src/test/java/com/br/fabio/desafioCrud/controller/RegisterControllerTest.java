package com.br.fabio.desafioCrud.controller;

import com.br.fabio.desafioCrud.repository.RegisterRepository;
import com.br.fabio.desafioCrud.service.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.br.fabio.desafioCrud.MockUtils.getVehicleRequest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = RegisterController.class)
class RegisterControllerTest {

    @InjectMocks
    private RegisterController controller;

    @MockBean
    private RegisterService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RegisterRepository repository;


    @Test
    void createCarTest() throws Exception {
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                                "/car").with(httpBasic("registerUser", "registerPassword"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(getVehicleRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }


    @Test
    void getCarByModelTest() throws Exception {
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        this.mockMvc.perform(MockMvcRequestBuilders.get(
                                "/car/model/ANY?available=true").with(httpBasic("registerUser", "registerPassword"))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getCarByBrandTest() throws Exception {
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        this.mockMvc.perform(MockMvcRequestBuilders.get(
                                "/car/brand/ANY?available=true").with(httpBasic("registerUser", "registerPassword"))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteCarTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/car/{id}", 1).with(httpBasic("registerUser", "registerPassword")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateCarTest() throws Exception {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/car/{id}", 2).with(httpBasic("registerUser", "registerPassword"))
                        .content(ow.writeValueAsString(getVehicleRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}