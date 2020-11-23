package es.auttonberri.exercise.controller;

import es.auttonberri.exercise.data.SpotRepository;
import es.auttonberri.exercise.data.TransactionRepository;
import es.auttonberri.exercise.exception.ExerciseException;
import es.auttonberri.exercise.model.domain.Transaction;
import es.auttonberri.exercise.model.request.CheckInRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("transaction")
public class TransactionController
{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    SpotRepository spotRepository;

    @PostMapping("check-in/{licensePlate}")
    public Transaction checkInCustomer(@PathVariable String licensePlate, @RequestBody CheckInRequestBody request)
    {

        //TODO could use a bean validator for this
        if (!StringUtils.hasText(request.getLotId()))
            throw new ExerciseException(HttpStatus.BAD_REQUEST, "Lot id is required");

        if(transactionRepository.existsByLicensePlateAndCheckOutTimeIsNull(licensePlate))
            throw new ExerciseException(HttpStatus.BAD_REQUEST, "Plate \"" + licensePlate + "\" has already been checked in.");

        var spot = (ObjectUtils.isEmpty(request.getDesiredSpotType())
            ? spotRepository.findFirstByLotAndOccupiedFalse(request.getLotId())
            : spotRepository.findFirstByLotAndTypeAndOccupiedFalse(request.getLotId(), request.getDesiredSpotType()))
                .orElseThrow( () -> new ExerciseException(HttpStatus.CONFLICT, "No spots of this type are available"));

        spot.occupy();
        return transactionRepository.saveAndFlush(
                new Transaction(0, spot, LocalDateTime.now(), null, licensePlate)
        );

    }

}
