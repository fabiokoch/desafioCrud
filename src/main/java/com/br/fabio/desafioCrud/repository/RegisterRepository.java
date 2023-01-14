package com.br.fabio.desafioCrud.repository;

import com.br.fabio.desafioCrud.model.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends MongoRepository<VehicleModel, String> {

    List<VehicleModel> findVehicleByModelIgnoreCaseAndAvailable(String model, boolean available);

    List<VehicleModel> findVehicleByBrandIgnoreCaseAndAvailable(String brand, boolean available);
}
