package pl.touk.ticketBooking.domain.Ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Movie.Movie;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private String type;
    @NonNull
    private float price;

    @ManyToOne
    @JoinColumn(name="timetable_id")
    @JsonIgnoreProperties("tickets")
    private Timetable timetable;

    @ManyToOne
    @JoinColumn(name="guest_id")
    @JsonIgnoreProperties("guests")
    private Guest guest;
}
