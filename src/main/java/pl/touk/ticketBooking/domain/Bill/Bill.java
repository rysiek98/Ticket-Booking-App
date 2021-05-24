package pl.touk.ticketBooking.domain.Bill;

import lombok.Builder;
import lombok.Getter;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import java.time.LocalTime;

@Getter
@Builder
public class Bill {
    private float totalCost;
    private LocalTime sessionTime;

    public static Bill createBill(Guest guest, LocalTime sessionTime){
        return Bill.builder()
                .totalCost((float) guest.getTickets().stream().mapToDouble(ticket -> ticket.getPrice()).sum())
                .sessionTime(sessionTime)
                .build();

    }

}
