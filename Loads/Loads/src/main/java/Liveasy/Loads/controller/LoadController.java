package Liveasy.Loads.controller;

import java.util.List;

import Liveasy.Loads.dto.LoadsDTO;
import Liveasy.Loads.entity.Loads;

import Liveasy.Loads.service.LoadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadsService loadService;

    @PostMapping
    public ResponseEntity<Loads> createLoad(@RequestBody LoadsDTO loadRequest) {
        Loads createdLoad = loadService.createLoad(loadRequest);
        return new ResponseEntity<>(createdLoad, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Loads>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String loadingPoint,
            @RequestParam(required = false) String unloadingPoint) {

        List<Loads> loads = loadService.getAllLoads(shipperId, truckType, productType,
                loadingPoint, unloadingPoint);
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Loads> getLoadById(@PathVariable String loadId) {
        Loads load = loadService.getLoadById(loadId);
        return ResponseEntity.ok(load);
    }
    @GetMapping("/shipper/{shipperId}")
    public ResponseEntity<List<Loads>> getLoadsByShipperId(@PathVariable String shipperId) {
        List<Loads> loads = loadService.getLoadsByShipperId(shipperId);
        return ResponseEntity.ok(loads);
    }


    @PutMapping("/{loadId}")
    public ResponseEntity<Loads> updateLoad(@PathVariable String loadId,
                                           @RequestBody LoadsDTO loadRequest) {
        Loads updatedLoad = loadService.updateLoad(loadId, loadRequest);
        return ResponseEntity.ok(updatedLoad);
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable String loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}