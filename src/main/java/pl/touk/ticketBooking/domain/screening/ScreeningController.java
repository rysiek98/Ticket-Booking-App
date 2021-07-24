package pl.touk.ticketBooking.domain.screening;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touk.ticketBooking.domain.bill.Bill;
import pl.touk.ticketBooking.domain.movie.Movie;
import pl.touk.ticketBooking.domain.room.Room;
import pl.touk.ticketBooking.domain.room.RoomDto;
import pl.touk.ticketBooking.domain.ticket.TicketRequestWrapper;

import java.util.List;

import static pl.touk.ticketBooking.domain.screening.ScreeningDtoMapper.mapToScreeningDto;
import static pl.touk.ticketBooking.domain.room.RoomDtoMapper.mapToRoomDto;

@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @GetMapping("/screening")
    public ResponseEntity<List<Screening>> getScreening(){
        return screeningService.getTimetable();
    }

    @GetMapping(value = "/screening/day/time")
    public ResponseEntity<List<ScreeningDto>> getRepertoire(@RequestBody ObjectNode objectNode){
        String day = objectNode.get("day").asText();
        String time = objectNode.get("time").asText();
        return new ResponseEntity<>(mapToScreeningDto(screeningService.getScreeningByDayTime(day,time)), HttpStatus.OK);
    }

    @GetMapping(value = "/screening/room")
    public ResponseEntity<RoomDto> getRoom(@RequestBody long screeningId){
        return new ResponseEntity<>(mapToRoomDto(screeningService.findById(screeningId)),HttpStatus.OK);
    }

    @PostMapping(value = "/screening/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Bill> addTickets(@RequestBody TicketRequestWrapper ticketRequestWrapper){
        return screeningService.addTickets(ticketRequestWrapper);
    }

    @PostMapping(value = "/cinema/movie")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return screeningService.addMovie(movie);
    }

    @PostMapping(value = "/cinema/room")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Room> addRoom(@RequestBody Room room){
        return screeningService.addRoom(room);
    }

    @PostMapping(value = "/cinema/screening")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Screening> addTimetable(@RequestBody ScreeningRequestWrapper screeningRequestWrapper){
        return screeningService.addScreening(screeningRequestWrapper);
    }
}
