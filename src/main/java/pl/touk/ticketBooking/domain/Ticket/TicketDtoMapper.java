package pl.touk.ticketBooking.domain.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketDtoMapper {

    private TicketDtoMapper(){}

    public static List<TicketDto> mapToTicketDtos(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(ticket -> mapToTicketDto(ticket))
                .collect(Collectors.toList());
    }

    private static TicketDto mapToTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .type(ticket.getType())
                .seatNumber(ticket.getSeatNumber())
                .price(countTicketPrice(ticket.getType(), ticket))
                .build();
    }

    private static float countTicketPrice(String type, Ticket ticket){
        switch (type){
            case "adult": ticket.setPrice(25);
            break;
            case "student": ticket.setPrice(18);
            break;
            case "child": ticket.setPrice(12.5f);
            break;
            default: ticket.setPrice(25);
        }
        return ticket.getPrice();
    }

}
