package es.auttonberri.exercise.data;

import es.auttonberri.exercise.model.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>
{

    Set<Spot> findByLotAndOccupiedTrue(String lotId);

    Set<Spot> findByLotAndOccupiedFalse(String lotId);

}
