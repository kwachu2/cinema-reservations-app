package pl.connectis.cinemareservationsapp.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SessionDTO {

    private long id;

    private long movieId;

    private long roomId;

    private ArrayList<Integer> reservedSeats;

    private LocalDateTime startTime;

}
