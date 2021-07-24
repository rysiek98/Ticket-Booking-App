package pl.touk.ticketBooking.domain.screening;

import lombok.Getter;

@Getter
public class ScreeningRequestWrapper {

    private long movieId;
    private long roomId;
    private Screening screening;
}
