package es.auttonberri.exercise.data;

import es.auttonberri.exercise.model.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{

    // To check for if the given license plate already checked in
    boolean existsByLicensePlateAndCheckOutTimeIsNull(String licensePlate);

}
