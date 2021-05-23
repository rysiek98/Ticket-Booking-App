package pl.touk.ticketBooking.domain.Guest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Ticket.Ticket;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "guests")
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;

    @OneToMany(mappedBy = "guest", targetEntity = Ticket.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("guest")
    private List<Ticket> tickets = new ArrayList<>();
}
