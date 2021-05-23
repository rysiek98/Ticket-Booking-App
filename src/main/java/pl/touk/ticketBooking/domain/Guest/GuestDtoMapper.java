package pl.touk.ticketBooking.domain.Guest;


import pl.touk.ticketBooking.domain.Ticket.TicketDtoMapper;


public class GuestDtoMapper {

    public static GuestDto GuestDtoMapper(Guest guest){
        return GuestDto.builder()
                .name(guest.getName())
                .surname(guest.getSurname())
                .tickets(TicketDtoMapper.mapToTicketDtos(guest.getTickets()))
                .totalCost((float) guest.getTickets().stream().mapToDouble(ticket -> ticket.getPrice()).sum())
                .build();
    }

}
