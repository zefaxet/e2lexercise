package es.auttonberri.exercise.model.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Spots")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Spot
{

    // TODO composite key with lot
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int number;

    @Column(nullable=false)
    private SpotType type;

    // this value could just be gleamed from the existence of an associated Transaction
    @Column(nullable=false)
    private boolean occupied;

    // could be NOT NULL, but this makes generating sample data easier
    private String lot;

    public void occupy()
    {
        setOccupied(true);
    }

    public void free()
    {
        setOccupied(false);
    }

    //listFreeSpots doesn't really make sense here the way I have this set up

}