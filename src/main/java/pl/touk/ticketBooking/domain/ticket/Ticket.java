package pl.touk.ticketBooking.domain.ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.screening.Screening;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NonNull
    @Column(name = "ticket_type")
    private String ticketType;
    private BigDecimal price;

    @NotNull
    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "session_date")
    private LocalDate sessionDate;

    @Column(name = "session_time")
    private LocalTime sessionTime;

    @ManyToOne
    @JoinColumn(name="screening_id")
    @JsonIgnoreProperties(value = {"tickets"})
    private Screening screening;

    @ManyToOne
    @JoinColumn(name="guest_id")
    @JsonIgnoreProperties(value = {"tickets"})
    private Guest guest;

    public void countTicketPrice(){
        switch (ticketType.toLowerCase(Locale.ROOT)){
            case "student": price = BigDecimal.valueOf(18);
                break;
            case "child": price = BigDecimal.valueOf(12.5);
                break;
            default: price = BigDecimal.valueOf(25);
        }
    }

    public void setTicketData(LocalTime sessionTime, LocalDate sessionDate, Screening screening, Guest guest){
        this.setSessionTime(sessionTime);
        this.setSessionDate(sessionDate);
        this.setScreening(screening);
        this.countTicketPrice();
        this.setGuest(guest);
    }

}
