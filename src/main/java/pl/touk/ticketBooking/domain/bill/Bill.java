package pl.touk.ticketBooking.domain.bill;

import lombok.Builder;
import lombok.Getter;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.ticket.Ticket;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Builder
public class Bill {
    private BigDecimal totalCost;
    private LocalTime reservationExpirationTime;

    public static Bill createBill(Guest guest, LocalTime sessionTime){
        return Bill.builder()
                .totalCost(calculateTotalCost(guest))
                .reservationExpirationTime(sessionTime)
                .build();

    }

    private static BigDecimal calculateTotalCost(Guest guest) {
        return guest.getTickets().stream().map(Ticket::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}
