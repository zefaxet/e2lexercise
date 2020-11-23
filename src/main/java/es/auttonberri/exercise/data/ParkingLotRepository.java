package es.auttonberri.exercise.data;

import es.auttonberri.exercise.model.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String>
{

    @Transactional
    @Modifying
    @Query("UPDATE ParkingLot l SET l.hourlyRate = ?1 WHERE l.lot = ?2")
    int updateRateById(double rate, String lotId);

}
