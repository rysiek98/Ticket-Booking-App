package pl.touk.ticketBooking.domain.Timetable;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class TimetableDto {
    private long id;
    private LocalTime session_time;
    private String movie_title;


    public static List<TimetableDto> sortByTitle(List<TimetableDto> timetables){
        return timetables.stream().sorted(Comparator.comparing(timetableDto -> timetableDto.movie_title)).collect(Collectors.toList());
    }

}
