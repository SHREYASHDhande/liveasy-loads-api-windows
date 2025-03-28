package Liveasy.Loads.repository;

import Liveasy.Loads.entity.Loads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<Loads, String> {
    List<Loads> findByShipperId(String shipperId);
    List<Loads> findByTruckType(String truckType);
    List<Loads> findByProductType(String productType);
    List<Loads> findByFacilityLoadingPoint(String loadingPoint);
    List<Loads> findByFacilityUnloadingPoint(String unloadingPoint);

    // Combined queries
    List<Loads> findByShipperIdAndTruckType(String shipperId, String truckType);
    List<Loads> findByShipperIdAndProductType(String shipperId, String productType);
    List<Loads> findByShipperIdAndFacilityLoadingPoint(String shipperId, String loadingPoint);
    List<Loads> findByShipperIdAndFacilityUnloadingPoint(String shipperId, String unloadingPoint);
}
