package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.touk.ticketBooking.domain.Bill.Bill;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Guest.GuestRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static pl.touk.ticketBooking.domain.DataValidation.ValidateInput.validateNameAndSurname;
import static pl.touk.ticketBooking.domain.Seat.Seat.reservingSeatsRules;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final GuestRepository guestRepository;

    public ResponseEntity<List<Timetable>> getTimetable() {
        return new ResponseEntity<>(timetableRepository.findAll(), HttpStatus.OK);
    }

    @SneakyThrows
    public List<Timetable> getRepertoire(String day, String sessionTime) {
        LocalDate date = LocalDate.parse(day);
        LocalTime time = LocalTime.parse(sessionTime, DateTimeFormatter.ofPattern("HH:mm"));
        return timetableRepository.findByDay(date, time);
    }

    @SneakyThrows
    public Timetable findById(long id) {
        return timetableRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    public ResponseEntity<Bill> addTickets(Guest guest, long id) {

        Timetable timetable = findById(id);
        LocalDate sessionDate = timetable.getSessionDate();
        LocalTime sessionTime = timetable.getSessionTime();
        guest.getTickets().sort(Comparator.comparingInt(t -> t.getSeatNumber()));
        timetable.getTickets().sort(Comparator.comparingInt(t -> t.getSeatNumber()));

        if(validateReservationTime(sessionTime, sessionDate)
                && !guest.getTickets().isEmpty()
                && reservingSeatsRules(guest.getTickets(), timetable.getTickets(),timetable.getRoom().getSeats())
                && validateNameAndSurname(guest)) {

            guest.addTickets(sessionTime, sessionDate, timetable);
            return new ResponseEntity<>(Bill.createBill(guestRepository.save(guest), timetable.getSessionTime()),HttpStatus.OK);
        }
            throw new IllegalArgumentException();
    }

    private boolean validateReservationTime(LocalTime sessionTime, LocalDate sessionDate){
        sessionTime.minusMinutes(15);
        return ( (sessionDate.equals(LocalDate.now()) && LocalTime.now().isBefore(sessionTime)) || LocalDate.now().isBefore(sessionDate) );
    }

}
