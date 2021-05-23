package pl.touk.ticketBooking.domain.Room;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomDto {
    private String room;
    private int freeSeats;
    private List<Integer> availableSeats;
}
