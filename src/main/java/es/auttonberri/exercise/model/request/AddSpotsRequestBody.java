package es.auttonberri.exercise.model.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddSpotsRequestBody
{

    private int countCovered;
    private int countUncovered;

}
