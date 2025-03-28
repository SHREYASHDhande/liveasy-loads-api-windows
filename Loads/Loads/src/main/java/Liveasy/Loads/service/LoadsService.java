// LoadsService.java
package Liveasy.Loads.service;

import Liveasy.Loads.dto.LoadsDTO;
import Liveasy.Loads.entity.Facility;
import Liveasy.Loads.entity.Loads;
import Liveasy.Loads.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoadsService {

    @Autowired
    private LoadRepository loadRepository;

    public Loads createLoad(LoadsDTO loadRequest) {
        Loads load = new Loads();
        String loadId = "load:" + UUID.randomUUID().toString();

        load.setLoadId(loadId);


        Facility facility = loadRequest.getFacility();
        if (facility == null) {
            facility = new Facility();
        }
        load.setFacility(facility);

        load.setProductType(loadRequest.getProductType());
        load.setTruckType(loadRequest.getTruckType());
        load.setNoOfTrucks(loadRequest.getNoOfTrucks());
        load.setWeight(loadRequest.getWeight());
        load.setComment(loadRequest.getComment());
        load.setShipperId(loadRequest.getShipperId());
        load.setDate(loadRequest.getDate());

        return loadRepository.save(load);
    }
    public List<Loads> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }


    public List<Loads> getAllLoads(String shipperId, String truckType, String productType,
                                   String loadingPoint, String unloadingPoint) {
        if (shipperId != null) {
            if (truckType != null) {
                return loadRepository.findByShipperIdAndTruckType(shipperId, truckType);
            } else if (productType != null) {
                return loadRepository.findByShipperIdAndProductType(shipperId, productType);
            } else if (loadingPoint != null) {
                return loadRepository.findByShipperIdAndFacilityLoadingPoint(shipperId, loadingPoint);
            } else if (unloadingPoint != null) {
                return loadRepository.findByShipperIdAndFacilityUnloadingPoint(shipperId, unloadingPoint);
            } else {
                return loadRepository.findByShipperId(shipperId);
            }
        } else if (truckType != null) {
            return loadRepository.findByTruckType(truckType);
        } else if (productType != null) {
            return loadRepository.findByProductType(productType);
        } else if (loadingPoint != null) {
            return loadRepository.findByFacilityLoadingPoint(loadingPoint);
        } else if (unloadingPoint != null) {
            return loadRepository.findByFacilityUnloadingPoint(unloadingPoint);
        } else {
            return loadRepository.findAll();
        }
    }

    public Loads getLoadById(String loadId) {
        return loadRepository.findById(loadId)
                .orElseThrow(() -> new RuntimeException("Load not found with id: " + loadId));
    }

    public Loads updateLoad(String loadId, LoadsDTO loadRequest) {
        Loads existingLoad = getLoadById(loadId);

        Facility facility = loadRequest.getFacility();
        if (facility == null) {
            facility = new Facility();
        }
        existingLoad.setFacility(facility);
        existingLoad.setProductType(loadRequest.getProductType());
        existingLoad.setTruckType(loadRequest.getTruckType());
        existingLoad.setNoOfTrucks(loadRequest.getNoOfTrucks());
        existingLoad.setWeight(loadRequest.getWeight());
        existingLoad.setComment(loadRequest.getComment());
        existingLoad.setDate(loadRequest.getDate());

        return loadRepository.save(existingLoad);
    }

    public void deleteLoad(String loadId) {
        Loads load = getLoadById(loadId);
        loadRepository.delete(load);
    }
}