
package pl.touk.ticketBooking.domain.screening;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.movie.Movie;
import pl.touk.ticketBooking.domain.room.Room;
import pl.touk.ticketBooking.domain.ticket.Ticket;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "screenings")
@Getter
@Setter
public class Screening {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    @Column(name = "session_time", columnDefinition = "TIME")
    private LocalTime sessionTime;
    @NonNull
    @Column(name = "session_date", columnDefinition = "DATE")
    private LocalDate sessionDate;
    @OneToMany(mappedBy = "screening", targetEntity = Ticket.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"screening"})
    private List<Ticket> tickets = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonIgnoreProperties(value = {"screenings"})
    private Movie movie;
    @ManyToOne
    @JoinColumn(name="room_id")
    @JsonIgnoreProperties(value = {"screenings"})
    private Room room;
}

