package pl.touk.ticketBooking.domain.Timetable;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimetableDTO {
    private long id;
    private LocalTime session_time;
    private String movie_title;
}
