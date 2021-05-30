package pl.touk.ticketBooking.domain.Timetable;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.touk.ticketBooking.domain.Bill.Bill;
import pl.touk.ticketBooking.domain.Guest.Guest;
import pl.touk.ticketBooking.domain.Guest.GuestRepository;
import pl.touk.ticketBooking.domain.Movie.Movie;
import pl.touk.ticketBooking.domain.Movie.MovieRepository;
import pl.touk.ticketBooking.domain.Room.Room;
import pl.touk.ticketBooking.domain.Room.RoomRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static pl.touk.ticketBooking.domain.DataValidation.ValidateInput.validateNameAndSurname;
import static pl.touk.ticketBooking.domain.DataValidation.ValidateInput.validateReservationTime;
import static pl.touk.ticketBooking.domain.Seat.Seat.reservingSeatsRules;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final GuestRepository guestRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

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
    @Transactional
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

    public ResponseEntity<Movie> addMovie(Movie movie) {
        return new ResponseEntity<>(movieRepository.save(movie), HttpStatus.OK);
    }

    public ResponseEntity<Room> addRoom(Room room) {
        room.createSeats();
        return new ResponseEntity<>(roomRepository.save(room), HttpStatus.OK);
    }

    @Transactional
    @SneakyThrows
    public ResponseEntity<Timetable> addTimetable(Timetable timetable, long movieId, long roomId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Room room = roomRepository.findById(roomId).orElseThrow();
        timetable.setMovie(movie);
        timetable.setRoom(room);
        return new ResponseEntity<>(timetableRepository.save(timetable), HttpStatus.OK);
    }
}
