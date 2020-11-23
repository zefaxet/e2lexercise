package es.auttonberri.exercise.model.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.IntStream;

@Entity
@Table(name="Lots")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParkingLot
{

    @Id
    private String lot;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="lot")
    private Set<Spot> spots;

    @Column(nullable=false)
    double hourlyRate;

    public void addCoveredSpots(int numberOfSpots)
    {

        addSpots(numberOfSpots, SpotType.COVERED);

    }

    public void addUncoveredSpots(int numberOfSpots)
    {

        addSpots(numberOfSpots, SpotType.UNCOVERED);

    }

    // just to comply with the uml
    public void changeHourlyRate(double newHourlyRate)
    {

        setHourlyRate(newHourlyRate);

    }

    private void addSpots(int count, SpotType type)
    {

        setSpots(IntStream.range(0, count).mapToObj(
                __ -> new Spot(0, type, false, lot)
        ).collect(this::getSpots, Set::add,
                (__, ___) -> {} // parallel accumulator doesn't matter here
        ));

    }
}
