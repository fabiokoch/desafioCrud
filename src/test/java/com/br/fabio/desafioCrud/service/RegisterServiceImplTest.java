package com.br.fabio.desafioCrud.service;

import br.com.fabio.desafioCrud.model.VehicleCreatedID;
import br.com.fabio.desafioCrud.model.VehicleResponseList;
import com.br.fabio.desafioCrud.exception.VehicleNotFoundException;
import com.br.fabio.desafioCrud.model.VehicleModel;
import com.br.fabio.desafioCrud.repository.RegisterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static com.br.fabio.desafioCrud.MockUtils.getVehicleModel;
import static com.br.fabio.desafioCrud.MockUtils.getVehicleRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegisterServiceImplTest {

    @Autowired
    RegisterService service;

    @MockBean
    private RegisterRepository repository;


    @Test
    void createCarTestSuccess() {
        when(repository.save(any())).thenReturn(getVehicleModel());
        VehicleCreatedID response = service.createCar(getVehicleRequest());
        assertNotNull(response);
        assertThat(response.getId(), is(getVehicleModel().getId()));
    }

    @Test
    void getCarByModelTestSuccess() {
        String model = "MODEL";
        when(repository.findVehicleByModelIgnoreCaseAndAvailable(model, true)).thenReturn(Collections.singletonList(getVehicleModel()));
        VehicleResponseList response = service.getCarByModel(model, true);
        assertNotNull(response);
    }

    @Test
    void getCarByModelTestWithNotFoundException() {
        String model = "MODEL";
        when(repository.findVehicleByModelIgnoreCaseAndAvailable(model, true)).thenReturn(Collections.EMPTY_LIST);

        assertThrows(VehicleNotFoundException.class, () -> service.getCarByModel(model, false));
    }

    @Test
    void getCarByBrandTestSuccess() {
        String brand = "BRAND";
        when(repository.findVehicleByBrandIgnoreCaseAndAvailable(brand, true)).thenReturn(Collections.singletonList(getVehicleModel()));
        VehicleResponseList response = service.getCarByBrand(brand, true);
        assertNotNull(response);
    }

    @Test
    void getCarByBrandTestWithNotFoundException() {
        String brand = "BRAND";
        when(repository.findVehicleByBrandIgnoreCaseAndAvailable(brand, true)).thenReturn(Collections.EMPTY_LIST);

        assertThrows(VehicleNotFoundException.class, () -> service.getCarByBrand(brand, false));
    }

    @Test
    void deleteCarTestSuccess() {
        VehicleModel model = getVehicleModel();
        when(repository.findById(model.getId())).thenReturn(java.util.Optional.of(model));

        service.deleteCar(model.getId());
        verify(repository, times(1)).findById(model.getId());
        verify(repository, times(1)).deleteById(model.getId());
    }

    @Test
    void updateCarTestSuccess() {
        String carId = "1";
        when(repository.findById(carId)).thenReturn(Optional.of(getVehicleModel()));
        service.updateCar(carId, getVehicleRequest());
        verify(repository, times(1)).findById(carId);
    }

}