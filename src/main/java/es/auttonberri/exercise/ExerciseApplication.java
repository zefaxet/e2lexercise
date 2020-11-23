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

		parkingLotRepository.saveAll(
			Arrays.asList(
				new ParkingLot("test",
					new HashSet<>(
						Arrays.asList(
							new Spot(0, SpotType.COVERED, false, null),
							new Spot(0, SpotType.UNCOVERED, false, null),
							new Spot(0, SpotType.COVERED, true, null),
							new Spot(0, SpotType.UNCOVERED, true, null),
							new Spot(0, SpotType.UNCOVERED, true, null)
						)
					),
			1.5
				)
			)
		);

		transactionRepository.saveAll(
			Arrays.asList(
				new Transaction(
					0,
					new Spot(3, SpotType.COVERED, true, null),
					LocalDateTime.now(),
					null,
					"T35T"
				)
			)
		);

		transactionRepository.flush();

	}

}
