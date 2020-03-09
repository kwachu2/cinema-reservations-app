package pl.connectis.cinemareservationsapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SeatDTO {

    private int rowNumber;

    private int seatNumber;

    private boolean sold;

}
