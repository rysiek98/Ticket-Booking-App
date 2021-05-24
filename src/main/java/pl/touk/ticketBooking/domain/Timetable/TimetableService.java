package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.touk.ticketBooking.domain.Bill.Bill;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Guest.GuestRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final GuestRepository guestRepository;

    public List<Timetable> getTimetable() {
        return timetableRepository.findAll();
    }

    public List<Timetable> getRepertoire(String day, String sessionTime) {
        LocalDate date = LocalDate.parse(day);
        LocalTime time = LocalTime.parse(sessionTime, DateTimeFormatter.ofPattern("HH:mm"));
        return timetableRepository.findByDay(date, time);
    }

    public Timetable findById(long id) {
        return timetableRepository.findById(id).orElseThrow();
    }

    public Bill addTicket(Guest guest, long id) {
        Timetable timetable = timetableRepository.findById(id).orElseThrow();
        guest.getTickets().forEach(ticket -> ticket.countTicketPrice());
        guest.getTickets().forEach(ticket -> ticket.setSessionTime(timetable.getSessionTime()));
        guest.getTickets().forEach(ticket -> ticket.setSessionDate(timetable.getSessionDate()));
        guest.getTickets().forEach(ticket -> ticket.setGuest(guest));
        guest.getTickets().forEach(ticket -> ticket.setTimetable(timetable));
        return Bill.createBill(guestRepository.save(guest), timetable.getSessionTime());
    }
}
