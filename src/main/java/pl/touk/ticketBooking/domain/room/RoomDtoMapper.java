package pl.touk.ticketBooking.domain.room;

import pl.touk.ticketBooking.domain.screening.Screening;
import pl.touk.ticketBooking.domain.seat.Seat;
import pl.touk.ticketBooking.domain.ticket.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDtoMapper {

    private RoomDtoMapper(){}

    public static RoomDto mapToRoomDto(Screening repertoire) {
        return RoomDto.builder()
                .room(repertoire.getRoom().getRoomName())
                .freeSeats(repertoire.getRoom().getNumberOfSeats()-repertoire.getTickets().size())
                .availableSeats(availableSeats(repertoire.getRoom().getSeats(), repertoire.getTickets()))
                .build();
    }

    private static List<Integer> availableSeats(List<Seat> seats, List<Ticket> tickets){
        if(tickets.isEmpty()){
            return seats.stream().map(Seat::getSeatNumber).collect(Collectors.toList());
        }else {
            return seats.stream()
                    .filter(seat -> tickets
                            .stream()
                            .noneMatch(ticket -> ticket.getSeatNumber() == seat.getSeatNumber()))
                    .map(Seat::getSeatNumber).collect(Collectors.toList());
            }
        }
}
