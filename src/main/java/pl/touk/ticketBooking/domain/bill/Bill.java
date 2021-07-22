package pl.touk.ticketBooking.domain.bill;

import lombok.Builder;
import lombok.Getter;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.ticket.Ticket;
import java.time.LocalTime;

@Getter
@Builder
public class Bill {
    private float totalCost;
    private LocalTime reservationExpirationTime;

    public static Bill createBill(Guest guest, LocalTime sessionTime){
        return Bill.builder()
                .totalCost((float) guest.getTickets().stream().mapToDouble(Ticket::getPrice).sum())
                .reservationExpirationTime(sessionTime)
                .build();

    }

}
