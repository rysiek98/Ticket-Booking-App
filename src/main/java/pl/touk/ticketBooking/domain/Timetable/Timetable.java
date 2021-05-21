
package pl.touk.ticketBooking.domain.Timetable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Movie.Movie;
import pl.touk.ticketBooking.domain.Room.Room;
import pl.touk.ticketBooking.domain.Ticket.Ticket;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "timetables")
public class Timetable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NonNull
    private LocalTime sessionTime;

    @NonNull
    private LocalDate sessionDate;

    @NonNull
    private int guestsNumber;

    @OneToMany(mappedBy = "timetable", targetEntity = Ticket.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("timetable")
    private Set<Ticket> tickets = new HashSet<Ticket>();

    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonIgnoreProperties("timetables")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="room_id")
    @JsonIgnoreProperties("timetables")
    private Room room;
}

