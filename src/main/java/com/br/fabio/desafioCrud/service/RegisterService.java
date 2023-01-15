package com.br.fabio.desafioCrud.service;


import br.com.fabio.desafioCrud.model.VehicleCreatedID;
import br.com.fabio.desafioCrud.model.VehicleRequest;
import br.com.fabio.desafioCrud.model.VehicleResponseList;

public interface RegisterService {

    VehicleCreatedID createCar(VehicleRequest vehicleRequest);

    VehicleResponseList getCarByModel(String model, Boolean isAvailable);

    VehicleResponseList getCarByBrand(String brand, Boolean isAvailable);

    void deleteCar(String carId);

    void updateCar(String id, VehicleRequest body);

}