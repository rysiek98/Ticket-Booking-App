package pl.touk.ticketBooking.domain.screening;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ScreeningDto {
    private long id;
    private LocalTime session_time;
    private String movie_title;


    public static List<ScreeningDto> sortByTitle(List<ScreeningDto> timetables){
        return timetables.stream().sorted(Comparator.comparing(screeningDto -> screeningDto.movie_title)).collect(Collectors.toList());
    }

}
