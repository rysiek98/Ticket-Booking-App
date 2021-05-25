package pl.touk.ticketBooking.domain.Room;

import org.springframework.http.ResponseEntity;
import pl.touk.ticketBooking.domain.Seat.Seat;
import pl.touk.ticketBooking.domain.Ticket.Ticket;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDtoMapper {

    private RoomDtoMapper(){}

    public static RoomDto mapToRoomDto(Timetable repertoire) {
        return RoomDto.builder()
                .room(repertoire.getRoom().getRoomName())
                .freeSeats(repertoire.getRoom().getNumberOfSeats()-repertoire.getTickets().size())
                .availableSeats(availableSeats(repertoire.getRoom().getSeats(), repertoire.getTickets()))
                .build();
    }

    private static List<Integer> availableSeats(List<Seat> seats, List<Ticket> tickets){
        if(tickets.isEmpty()){
            return seats.stream().map(seat -> seat.getSeatNumber()).collect(Collectors.toList());
        }else {
            return seats.stream()
                    .filter(seat -> tickets
                            .stream()
                            .noneMatch(ticket -> ticket.getSeatNumber() == seat.getSeatNumber()))
                    .map(seat -> seat.getSeatNumber()).collect(Collectors.toList());
            }
        }
}
