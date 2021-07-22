package pl.touk.ticketBooking.domain.screening;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touk.ticketBooking.domain.bill.Bill;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.movie.Movie;
import pl.touk.ticketBooking.domain.room.Room;
import pl.touk.ticketBooking.domain.room.RoomDto;
import java.util.List;

import static pl.touk.ticketBooking.domain.screening.ScreeningDtoMapper.mapToTimeTableDtos;
import static pl.touk.ticketBooking.domain.room.RoomDtoMapper.mapToRoomDto;

@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @GetMapping("/screening")
    public ResponseEntity<List<Screening>> getScreening(){
        return screeningService.getTimetable();
    }

    @GetMapping(value = "/repertoire/{day}/{time}")
    public ResponseEntity<List<ScreeningDto>> getRepertoire(@PathVariable String day, @PathVariable String time){
        return new ResponseEntity<>(mapToTimeTableDtos(screeningService.getRepertoire(day, time)), HttpStatus.OK);
    }

    @GetMapping(value = "/repertoire/movie/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable long id){
        return new ResponseEntity<>(mapToRoomDto(screeningService.findById(id)),HttpStatus.OK);
    }

    @PostMapping(value = "/repertoire/movie/{id}/buy")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Bill> addTickets(@PathVariable long id, @RequestBody Guest guest){
        return screeningService.addTickets(guest,id);
    }

    @PostMapping(value = "/cinema/movie/add")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return screeningService.addMovie(movie);
    }

    @PostMapping(value = "/cinema/room/add")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Room> addRoom(@RequestBody Room room){
        return screeningService.addRoom(room);
    }

    @PostMapping(value = "/cinema/screening/add/{movieId}/{roomId}")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Screening> addTimetable(@PathVariable long movieId, @PathVariable long roomId, @RequestBody Screening screening){
        return screeningService.addTimetable(screening, movieId, roomId);
    }



}
