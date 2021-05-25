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
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final GuestRepository guestRepository;

    public ResponseEntity<List<Timetable>> getTimetable() {
        return new ResponseEntity<>(timetableRepository.findAll(), HttpStatus.OK);
    }

    public List<Timetable> getRepertoire(String day, String sessionTime) {
        LocalDate date = LocalDate.parse(day);
        LocalTime time = LocalTime.parse(sessionTime, DateTimeFormatter.ofPattern("HH:mm"));
        return timetableRepository.findByDay(date, time);
    }

    public Timetable findById(long id) {
        return timetableRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    public ResponseEntity<Bill> addTicket(Guest guest, long id) {
        Timetable timetable = timetableRepository.findById(id).orElseThrow();
        LocalDate sessionDate = timetable.getSessionDate();
        LocalTime sessionTime = timetable.getSessionTime();
        if(addTicketLogic(sessionTime, sessionDate)) {
            guest.getTickets().forEach(ticket -> ticket.countTicketPrice());
            guest.getTickets().forEach(ticket -> ticket.setSessionTime(sessionTime));
            guest.getTickets().forEach(ticket -> ticket.setSessionDate(sessionDate));
            guest.getTickets().forEach(ticket -> ticket.setGuest(guest));
            guest.getTickets().forEach(ticket -> ticket.setTimetable(timetable));
            return new ResponseEntity<>(Bill.createBill(guestRepository.save(guest), timetable.getSessionTime()),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Bill.builder().build(), BAD_REQUEST);
        }

    }

    private boolean addTicketLogic(LocalTime sessionTime, LocalDate sessionDate){
        sessionTime.minusMinutes(15);
        return ( (sessionDate.equals(LocalDate.now()) && LocalTime.now().isBefore(sessionTime)) || LocalDate.now().isBefore(sessionDate) );
    }
}
