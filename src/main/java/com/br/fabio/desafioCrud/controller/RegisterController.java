package com.br.fabio.desafioCrud.controller;


import br.com.fabio.desafioCrud.api.CarApi;
import br.com.fabio.desafioCrud.model.VehicleCreatedID;
import br.com.fabio.desafioCrud.model.VehicleRequest;
import br.com.fabio.desafioCrud.model.VehicleResponseList;
import com.br.fabio.desafioCrud.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController implements CarApi {

    @Autowired
    private RegisterService registerService;

    @Override
    public ResponseEntity<VehicleCreatedID> createCar(VehicleRequest vehicleRequest) {
        log.info("Starting createCar method with request, {}", vehicleRequest);

        VehicleCreatedID response = registerService.createCar(vehicleRequest);

        log.info("Finishing method createCar with success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<VehicleResponseList> getCarByModel(String model) {
        log.info("Starting getCarByModel method ");

        VehicleResponseList response = registerService.getCarByModel(model);

        log.info("Finishing method getCarByModel with success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<VehicleResponseList> getCarByBrand(String brand) {
        log.info("Starting getCarByBrand method ");

        VehicleResponseList response = registerService.getCarByBrand(brand);

        log.info("Finishing method getCarByBrand with success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
