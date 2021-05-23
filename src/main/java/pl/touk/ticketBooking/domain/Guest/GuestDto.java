package pl.touk.ticketBooking.domain.Guest;

import lombok.Builder;
import lombok.Getter;
import pl.touk.ticketBooking.domain.Ticket.TicketDto;

import java.util.List;

@Getter
@Builder
public class GuestDto {

    private String name;
    private String surname;
    private float totalCost;
    List<TicketDto> tickets;
}
