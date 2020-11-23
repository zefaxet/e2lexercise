package es.auttonberri.exercise;

import es.auttonberri.exercise.data.ParkingLotRepository;
import es.auttonberri.exercise.data.SpotRepository;
import es.auttonberri.exercise.data.TransactionRepository;
import es.auttonberri.exercise.model.domain.ParkingLot;
import es.auttonberri.exercise.model.domain.Spot;
import es.auttonberri.exercise.model.domain.SpotType;
import es.auttonberri.exercise.model.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class ExerciseApplication implements CommandLineRunner
{

	@Autowired
	ParkingLotRepository parkingLotRepository;

	@Autowired
	SpotRepository spotRepository;

	@Autowired
	TransactionRepository transactionRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@Override
	public void run(String... args)
	{

		// load up the database with some useful sample data
		// I couldn't get h2 or hibernate to find their data/import sql scripts on the classpath so I'm doing it here

		var spots = new ArrayList<>(parkingLotRepository.saveAll(
			Arrays.asList(
				new ParkingLot("test",
					new HashSet<>(
						Arrays.asList(
							new Spot(0, SpotType.COVERED, false, "test"),
							new Spot(0, SpotType.COVERED, false, "test"),
							new Spot(0, SpotType.COVERED, false, "test"),
							new Spot(0, SpotType.COVERED, false, "test"),
							new Spot(0, SpotType.COVERED, false, "test"),
							new Spot(0, SpotType.UNCOVERED, false, "test"),
							new Spot(0, SpotType.UNCOVERED, false, "test"),
							new Spot(0, SpotType.UNCOVERED, false, "test"),
							new Spot(0, SpotType.UNCOVERED, false, "test"),
							new Spot(0, SpotType.UNCOVERED, false, "test")
						)
					),
			1.5
				)
			)
		).get(0).getSpots());

		spots.get(0).setOccupied(true);
		spots.get(1).setOccupied(true);
		spots.get(2).setOccupied(true);
		spots.get(3).setOccupied(true);

		spotRepository.saveAndFlush(spots.get(0));
		spotRepository.saveAndFlush(spots.get(1));
		spotRepository.saveAndFlush(spots.get(2));
		spotRepository.saveAndFlush(spots.get(3));

		transactionRepository.saveAll(
			Arrays.asList(
				new Transaction(
					0,
					spots.get(0),
					LocalDateTime.now(),
					null,
					"T35T"
				),
				new Transaction(
					0,
					spots.get(1),
					LocalDateTime.now(),
					LocalDateTime.now(),
					"T35T2"
				),
				new Transaction(
					0,
					spots.get(2),
					LocalDateTime.now(),
					LocalDateTime.now().plusHours(3),
					"T35T3"
				),
				new Transaction(
					0,
					spots.get(3),
					LocalDateTime.now(),
					LocalDateTime.now(),
					"T35T4"
				)
			)
		);

		transactionRepository.flush();

	}

}
