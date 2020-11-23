package es.auttonberri.exercise.controller;

import es.auttonberri.exercise.data.ParkingLotRepository;
import es.auttonberri.exercise.exception.ExerciseException;
import es.auttonberri.exercise.model.domain.ParkingLot;
import es.auttonberri.exercise.model.domain.Spot;
import es.auttonberri.exercise.model.domain.SpotType;
import es.auttonberri.exercise.model.request.AddSpotsRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ParkingLotControllerTest {

    @InjectMocks
    ParkingLotController parkingLotController;

    @Mock
    ParkingLotRepository parkingLotRepository;

    final Answer<?> returnSingleGivenArgument = invocation -> invocation.getArgument(0);

    @Test
    void spotsAreAdded()
    {

        var lot = new ParkingLot();
        lot.setSpots(new HashSet<>());
        var spot = new Spot();
        spot.setType(SpotType.COVERED);
        lot.getSpots().add(spot);

        when(parkingLotRepository.findById(any())).thenReturn(Optional.of(lot));
        when(parkingLotRepository.saveAndFlush(any())).then(returnSingleGivenArgument);

        lot = parkingLotController.addSpots("", new AddSpotsRequestBody(1, 2));

        var nCovered = 0;
        var nUncovered = 0;

        for (var s : lot.getSpots())
            switch (s.getType())
            {
                case COVERED:
                    nCovered++;
                    break;
                case UNCOVERED:
                    nUncovered++;
                    break;
                default:
                    fail();
            }

        assertEquals(nCovered, 2);
        assertEquals(nUncovered, 2);

    }

    @Test()
    void http404WhenLotIsNotFound()
    {

        when(parkingLotRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(
            ExerciseException.class,
            () -> parkingLotController.addSpots("", null)
        );

    }
}
