package com.br.fabio.desafioCrud;

import br.com.fabio.desafioCrud.model.VehicleRequest;
import com.br.fabio.desafioCrud.model.VehicleModel;

public class MockUtils {

    public static VehicleRequest getVehicleRequest() {
        VehicleRequest request = new VehicleRequest();
        request.setAvailable(true);
        request.setBrand("BMW");
        request.setModel("4002");
        return request;
    }

    public static VehicleModel getVehicleModel() {
        VehicleModel model = new VehicleModel();
        model.setAvailable(true);
        model.setBrand("BMW");
        model.setModel("4002");
        model.setId("1");
        return model;
    }

}
