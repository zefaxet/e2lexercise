package es.auttonberri.exercise.model.request;

import es.auttonberri.exercise.model.domain.SpotType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckInRequestBody
{

    private String lotId;
    private SpotType desiredSpotType;

}
