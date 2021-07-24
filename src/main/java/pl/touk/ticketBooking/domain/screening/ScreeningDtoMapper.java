package pl.touk.ticketBooking.domain.screening;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.touk.ticketBooking.domain.screening.ScreeningDto.sortByTitle;

public class ScreeningDtoMapper {

    private ScreeningDtoMapper(){}

    public static List<ScreeningDto> mapToScreeningDto(List<Screening> repertoires) {
        return sortByTime(sortByTitle(repertoires.stream()
                .map(ScreeningDtoMapper::mapToScreeningDto)
                .collect(Collectors.toList())));
    }

    private static ScreeningDto mapToScreeningDto(Screening repertoire) {
        return ScreeningDto.builder()
                .id(repertoire.getId())
                .session_time(repertoire.getSessionTime())
                .movie_title(repertoire.getMovie().getTitle())
                .build();
    }

    private static List<ScreeningDto> sortByTime(List<ScreeningDto> timetables){
       return timetables.stream().sorted(Comparator.comparingInt(t -> t.getSession_time().toSecondOfDay())).collect(Collectors.toList());

    }

}

