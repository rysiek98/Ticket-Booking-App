package pl.touk.ticketBooking.domain.guest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.screening.Screening;
import pl.touk.ticketBooking.domain.ticket.Ticket;
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
    @JsonIgnoreProperties(value = {"guest"}, allowSetters = true)
    private List<Ticket> tickets = new ArrayList<>();

    public void createTickets(LocalTime sessionTime, LocalDate sessionDate, Screening screening){
        tickets.forEach(ticket -> ticket.setTicketData(sessionTime, sessionDate, screening, this));
    }

}
