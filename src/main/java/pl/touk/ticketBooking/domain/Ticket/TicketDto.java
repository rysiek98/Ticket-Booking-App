package pl.touk.ticketBooking.domain.Ticket;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketDto {

    private String type;
    private float price;
    private int seatNumber;
}
