package pl.touk.ticketBooking.domain.ticket;

import lombok.Getter;
import pl.touk.ticketBooking.domain.guest.Guest;

@Getter
public class TicketRequestWrapper {

    private long screeningId;
    private Guest guest;
}
