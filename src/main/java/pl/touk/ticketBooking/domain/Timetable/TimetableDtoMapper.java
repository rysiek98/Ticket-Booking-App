package pl.touk.ticketBooking.domain.Timetable;

import java.util.List;
import java.util.stream.Collectors;

public class TimetableDtoMapper {

    private TimetableDtoMapper(){}
    public static List<TimetableDTO> mapToTimeTableDtos(List<Timetable> repertoires) {
        return repertoires.stream()
                .map(repertoire -> mapToTimeTableDto(repertoire))
                .collect(Collectors.toList());
    }

    private static TimetableDTO mapToTimeTableDto(Timetable repertoire) {
        return TimetableDTO.builder()
                .session_time(repertoire.getSessionTime())
                .session_date(repertoire.getSessionDate())
                .movie_title(repertoire.getMovie().getTitle())
                .build();
    }
}
