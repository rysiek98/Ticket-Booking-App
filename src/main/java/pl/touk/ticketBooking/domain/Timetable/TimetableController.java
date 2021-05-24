package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Timetable> getTimetable(){
        return timetableService.getTimetable();
    }

    @GetMapping(value = "/repertoire/{day}/{time}")
    public List<TimetableDto> getRepertoire(@PathVariable String day, @PathVariable String time){
        return mapToTimeTableDtos(timetableService.getRepertoire(day, time));
    }

    @GetMapping(value = "/repertoire/movie/{id}")
    public RoomDto getRoom(@PathVariable long id){
        return mapToRoomDto(timetableService.findById(id));
    }

    @PostMapping(value = "/repertoire/movie/{id}/buy")
    @ResponseStatus(HttpStatus.CREATED)
    private Bill addTicket(@PathVariable long id, @RequestBody Guest guest){
        return timetableService.addTicket(guest,id);
    }

}
