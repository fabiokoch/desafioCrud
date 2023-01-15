package com.br.fabio.desafioCrud.service;


import br.com.fabio.desafioCrud.model.VehicleCreatedID;
import br.com.fabio.desafioCrud.model.VehicleRequest;
import br.com.fabio.desafioCrud.model.VehicleResponseList;
import com.br.fabio.desafioCrud.exception.VehicleNotFoundException;
import com.br.fabio.desafioCrud.model.VehicleModel;
import com.br.fabio.desafioCrud.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    RegisterRepository repository;

    @Transactional
    public VehicleCreatedID createCar(VehicleRequest vehicleRequest) {
        log.info("Starting method createVehicle service");
        VehicleModel model = new VehicleModel();
        mapper.map(vehicleRequest, model);

        try {
            model = repository.save(model);
        } catch (Exception e) {
            log.error("Error during db save {}", e.getMessage());
            throw new RuntimeException();
        }

        VehicleCreatedID response = new VehicleCreatedID();
        mapper.map(model, response);

        log.info("Finishing method createVehicle with vehicle ID {} ", response.getId());
        return response;
    }

    @Override
    public VehicleResponseList getCarByModel(String model, Boolean isAvailable) {
        log.info("Starting method getCarByModel service");
        List<VehicleModel> vehicleModel = repository.findVehicleByModelIgnoreCaseAndAvailable(model, isAvailable);

        if (vehicleModel.isEmpty()) {
            throw new VehicleNotFoundException();
        }

        VehicleResponseList response
                = mapper.map(vehicleModel, new TypeToken<VehicleResponseList>() {
        }.getType());

        log.info("Finishing method getCarByModel with response  {} ", response);
        return response;
    }

    @Override
    public VehicleResponseList getCarByBrand(String brand, Boolean isAvailable) {
        log.info("Starting method getCarByBrand service");
        List<VehicleModel> vehicleModel = repository.findVehicleByBrandIgnoreCaseAndAvailable(brand, isAvailable);

        if (vehicleModel.isEmpty()) {
            throw new VehicleNotFoundException();
        }

        VehicleResponseList response
                = mapper.map(vehicleModel, new TypeToken<VehicleResponseList>() {
        }.getType());

        log.info("Finishing method getCarByBrand with response  {} ", response);
        return response;
    }

    @Override
    @Transactional
    public void deleteCar(String carId) {
        log.info("Starting method deleteCar service");
        repository.findById(carId).orElseThrow(VehicleNotFoundException::new);

        repository.deleteById(carId);
        log.info("Finishing method deleteCar");
    }

    @Override
    @Transactional
    public void updateCar(String id, VehicleRequest vehicleRequest) {
        log.info("Starting method updateCar service");
        VehicleModel vehicleModel = repository.findById(id).orElseThrow(VehicleNotFoundException::new);
        vehicleModel.setModel(vehicleRequest.getModel() == null ? vehicleModel.getModel() : vehicleRequest.getModel());
        vehicleModel.setAvailable(vehicleRequest.isAvailable() == null ? vehicleModel.getAvailable() : vehicleRequest.isAvailable());
        vehicleModel.setBrand(vehicleRequest.getBrand() == null ? vehicleModel.getBrand() : vehicleRequest.getBrand());

        try {
            repository.save(vehicleModel);
        } catch (Exception e) {
            log.error("Error during db save {}", e.getMessage());
            throw new RuntimeException();
        }

        log.info("Finishing method updateCar");
    }

}
