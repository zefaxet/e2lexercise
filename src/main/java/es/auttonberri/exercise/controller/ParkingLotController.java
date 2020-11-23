package es.auttonberri.exercise.controller;

import es.auttonberri.exercise.data.ParkingLotRepository;
import es.auttonberri.exercise.data.SpotRepository;
import es.auttonberri.exercise.exception.ExerciseException;
import es.auttonberri.exercise.model.domain.ParkingLot;
import es.auttonberri.exercise.model.domain.Spot;
import es.auttonberri.exercise.model.request.AddSpotsRequestBody;
import es.auttonberri.exercise.model.request.PatchRateRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("lot/{lotId}")
public class ParkingLotController
{

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    SpotRepository spotRepository;

    @PostMapping("spots/add")
    public ParkingLot addSpots(@PathVariable("lotId") String lotId, @RequestBody AddSpotsRequestBody request)
    {

        // get the parking lot and check that it's valid
        final var parkingLot = parkingLotRepository.findById(lotId).orElseThrow(
                () -> new ExerciseException(HttpStatus.NOT_FOUND, "Parking lot \"" + lotId + "\" does not exist.")
        );

        // TODO could also just use an insert on the spots repo
        parkingLot.addCoveredSpots(request.getCountCovered());
        parkingLot.addUncoveredSpots(request.getCountUncovered());

        return parkingLotRepository.saveAndFlush(parkingLot);

    }

    @GetMapping("spots/occupied")
    public Set<Spot> listFilledSpots(@PathVariable("lotId") String lotId)
    {
        var spots = spotRepository.findByLotAndOccupiedTrue(lotId);
        if (spots.isEmpty() && !parkingLotRepository.existsById(lotId))
            throw new ExerciseException(HttpStatus.NOT_FOUND, "Parking lot \"" + lotId + "\" does not exist.");
        return spots;
    }

    @GetMapping("spots/unoccupied")
    public Set<Spot> listFreeSpots(@PathVariable("lotId") String lotId)
    {
        var spots = spotRepository.findByLotAndOccupiedFalse(lotId);
        if (spots.isEmpty() && !parkingLotRepository.existsById(lotId))
            throw new ExerciseException(HttpStatus.NOT_FOUND, "Parking lot \"" + lotId + "\" does not exist.");
        return spots;
    }

    @PatchMapping("rate")
    public void patchRate(@PathVariable("lotId") String lotId, @RequestBody PatchRateRequestBody request)
    {

        var result = parkingLotRepository.updateRateById(request.getRate(), lotId);

        if (result < 1)
            throw new ExerciseException(HttpStatus.NOT_FOUND, "Parking lot \"" + lotId + "\" does not exist.");

        // Not the best way to notify acceptance (slow) but it was easy for me for this exercise
        throw new ExerciseException(HttpStatus.ACCEPTED);

    }

}
