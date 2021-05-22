package pl.touk.ticketBooking.domain.Timetable;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class TimetableDTO {

    private LocalTime session_time;
    private LocalDate session_date;
    private String movie_title;
}
