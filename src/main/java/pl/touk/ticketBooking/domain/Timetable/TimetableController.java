package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.touk.ticketBooking.domain.Room.RoomDTO;
import pl.touk.ticketBooking.domain.Room.RoomDtoMapper;

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
    public List<TimetableDTO> getRepertoire(@PathVariable String day, @PathVariable String time){
        return mapToTimeTableDtos(timetableService.getRepertoire(day, time));
    }

    @GetMapping(value = "/repertoire/movie/{id}")
    public RoomDTO getRoom(@PathVariable long id){
        return mapToRoomDto(timetableService.findById(id));
    }
    
}
