package com.br.fabio.desafioCrud.security;

import com.br.fabio.desafioCrud.controller.RegisterController;
import com.br.fabio.desafioCrud.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {
                BasicAuthWebSecurityConfiguration.class,
                RegisterController.class
        }
)
@AutoConfigureMockMvc
public class BasicAuthTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private RegisterController controller;

    @MockBean
    private RegisterService service;

    @Test
    void testingRequestWithWrongBasicAuth() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/car/model/ANY?available=true")
                        .with(httpBasic("user","wrong-password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testingRequestWithBasicAuth() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/car/model/ANY?available=true")
                        .with(httpBasic("registerUser", "registerPassword")))
                .andExpect(status().isOk());
    }

}
