package es.auttonberri.exercise.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="Transactions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int transactionId;

    @OneToOne(optional=false)
    private Spot spot;

    @Column(nullable=false)
    private LocalDateTime checkInTime; // could be NOT NULL

    private LocalDateTime checkOutTime;

    // charge is an intrinsic property thus not really necessary

    @Column(nullable=false)
    private String licensePlate;

    public void checkIn(/*licensePlate will be known*/)
    {
        setCheckInTime(LocalDateTime.now());
    }

    public void checkOut(/*licensePlate will be known*/)
    {
        setCheckOutTime(LocalDateTime.now());
    }

    // the listTransactionsBy... methods don't really make sense here the way this has been set up

}
