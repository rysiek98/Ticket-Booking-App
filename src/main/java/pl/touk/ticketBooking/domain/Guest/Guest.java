package pl.touk.ticketBooking.domain.Guest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Ticket.Ticket;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public static void addTickets(Guest guest, LocalTime sessionTime, LocalDate sessionDate, Timetable timetable){
        guest.getTickets().forEach(Ticket::countTicketPrice);
        guest.getTickets().forEach(ticket -> ticket.setSessionTime(sessionTime));
        guest.getTickets().forEach(ticket -> ticket.setSessionDate(sessionDate));
        guest.getTickets().forEach(ticket -> ticket.setGuest(guest));
        guest.getTickets().forEach(ticket -> ticket.setTimetable(timetable));
    }

}
