package pl.touk.ticketBooking.domain.ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.screening.Screening;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    private float price;

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
        switch (ticketType){
            case "adult": price = 25;
                break;
            case "student": price = 18;
                break;
            case "child": price = 12.5f;
                break;
            case "Adult": price = 25;
                break;
            case "Student": price = 18;
                break;
            case "Child": price = 12.5f;
                break;
            default: price = 25;
        }
    }

}
