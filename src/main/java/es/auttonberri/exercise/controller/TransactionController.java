package es.auttonberri.exercise.controller;

import es.auttonberri.exercise.model.domain.Transaction;
import es.auttonberri.exercise.model.request.CheckInRequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class TransactionController
{

    @PostMapping("check-in/{licensePlate}")
    public Transaction checkInCustomer(@PathVariable String licensePlate, @RequestBody CheckInRequestBody request)
    {

        return null;

    }

}
