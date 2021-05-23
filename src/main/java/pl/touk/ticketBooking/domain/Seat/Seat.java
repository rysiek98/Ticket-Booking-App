package pl.touk.ticketBooking.domain.Seat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Room.Room;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "seats")
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotNull
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name="room_id")
    @JsonIgnoreProperties("seats")
    private Room room;

}
