package pl.touk.ticketBooking.domain.Guest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Ticket.Ticket;
import pl.touk.ticketBooking.domain.Timetable.Timetable;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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

    public void addTickets(LocalTime sessionTime, LocalDate sessionDate, Timetable timetable){
        tickets.forEach(Ticket::countTicketPrice);
        tickets.forEach(ticket -> ticket.setSessionTime(sessionTime));
        tickets.forEach(ticket -> ticket.setSessionDate(sessionDate));
        tickets.forEach(ticket -> ticket.setGuest(this));
        tickets.forEach(ticket -> ticket.setTimetable(timetable));
    }

}
