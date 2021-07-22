
package pl.touk.ticketBooking.domain.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.touk.ticketBooking.domain.screening.Screening;
import pl.touk.ticketBooking.domain.seat.Seat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NotNull
    private String roomName;
    @NotNull
    private int numberOfSeats;

    @OneToMany(mappedBy = "room", targetEntity = Screening.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"room"})
    private List<Screening> screenings = new ArrayList<>();

    @OneToMany(mappedBy = "room", targetEntity = Seat.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"room"})
    private List<Seat> seats = new ArrayList<>();

    public void createSeats(){
        Seat seat;
        for(int i = 0; i < numberOfSeats; i++){
            seat = new Seat();
            seat.setSeatNumber(i+1);
            seat.setRoom(this);
            seats.add(seat);
        }
        seat = null;
    }

}
