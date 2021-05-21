package pl.touk.ticketBooking.domain.Ticket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketsService {

    private final TicketRepository ticketRepository;
}
