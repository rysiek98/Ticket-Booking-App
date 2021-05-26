package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touk.ticketBooking.domain.Bill.Bill;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Room.RoomDto;
import java.util.List;

import static pl.touk.ticketBooking.domain.Timetable.TimetableDtoMapper.mapToTimeTableDtos;
import static pl.touk.ticketBooking.domain.Room.RoomDtoMapper.mapToRoomDto;

@RestController
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/timetable")
    public ResponseEntity<List<Timetable>> getTimetable(){
        return timetableService.getTimetable();
    }

    @GetMapping(value = "/repertoire/{day}/{time}")
    public ResponseEntity<List<TimetableDto>> getRepertoire(@PathVariable String day, @PathVariable String time){
        return new ResponseEntity<>(mapToTimeTableDtos(timetableService.getRepertoire(day, time)), HttpStatus.OK);
    }

    @GetMapping(value = "/repertoire/movie/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable long id){
        return new ResponseEntity<>(mapToRoomDto(timetableService.findById(id)),HttpStatus.OK);
    }

    @PostMapping(value = "/repertoire/movie/{id}/buy")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Bill> addTicket(@PathVariable long id, @RequestBody Guest guest){
        return timetableService.addTickets(guest,id);
    }

}
