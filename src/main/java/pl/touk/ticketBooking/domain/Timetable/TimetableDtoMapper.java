package pl.touk.ticketBooking.domain.Timetable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.touk.ticketBooking.domain.Timetable.TimetableDto.sortByTitle;

public class TimetableDtoMapper {

    private TimetableDtoMapper(){}

    public static List<TimetableDto> mapToTimeTableDtos(List<Timetable> repertoires) {
        return sortByTime(sortByTitle(repertoires.stream()
                .map(repertoire -> mapToTimeTableDto(repertoire))
                .collect(Collectors.toList())));
    }

    private static TimetableDto mapToTimeTableDto(Timetable repertoire) {
        return TimetableDto.builder()
                .id(repertoire.getId())
                .session_time(repertoire.getSessionTime())
                .movie_title(repertoire.getMovie().getTitle())
                .build();
    }

    private static List<TimetableDto> sortByTime(List<TimetableDto> timetables){
       return timetables.stream().sorted(Comparator.comparingInt(t -> t.getSession_time().toSecondOfDay())).collect(Collectors.toList());

    }

}

