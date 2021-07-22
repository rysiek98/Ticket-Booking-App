package pl.touk.ticketBooking.domain.screening;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.touk.ticketBooking.domain.bill.Bill;
import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.guest.GuestRepository;
import pl.touk.ticketBooking.domain.movie.Movie;
import pl.touk.ticketBooking.domain.movie.MovieRepository;
import pl.touk.ticketBooking.domain.room.Room;
import pl.touk.ticketBooking.domain.room.RoomRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static pl.touk.ticketBooking.domain.dataValidation.ValidateInput.validateNameAndSurname;
import static pl.touk.ticketBooking.domain.dataValidation.ValidateInput.validateReservationTime;
import static pl.touk.ticketBooking.domain.seat.Seat.reservingSeatsRules;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final GuestRepository guestRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public ResponseEntity<List<Screening>> getTimetable() {
        return new ResponseEntity<>(screeningRepository.findAll(), HttpStatus.OK);
    }

    @SneakyThrows
    public List<Screening> getRepertoire(String day, String sessionTime) {
        LocalDate date = LocalDate.parse(day);
        LocalTime time = LocalTime.parse(sessionTime, DateTimeFormatter.ofPattern("HH:mm"));
        return screeningRepository.findByDay(date, time);
    }

    @SneakyThrows
    public Screening findById(long id) {
        return screeningRepository.findById(id).orElseThrow();
    }

    @SneakyThrows
    @Transactional
    public ResponseEntity<Bill> addTickets(Guest guest, long id) {
        Screening screening = findById(id);
        LocalDate sessionDate = screening.getSessionDate();
        LocalTime sessionTime = screening.getSessionTime();
        guest.getTickets().sort(Comparator.comparingInt(t -> t.getSeatNumber()));
        screening.getTickets().sort(Comparator.comparingInt(t -> t.getSeatNumber()));
        if(validateReservationTime(sessionTime, sessionDate)
                && !guest.getTickets().isEmpty()
                && reservingSeatsRules(guest.getTickets(), screening.getTickets(), screening.getRoom().getSeats())
                && validateNameAndSurname(guest)) {
            guest.addTickets(sessionTime, sessionDate, screening);
            return new ResponseEntity<>(Bill.createBill(guestRepository.save(guest), screening.getSessionTime()),HttpStatus.OK);
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
    public ResponseEntity<Screening> addTimetable(Screening screening, long movieId, long roomId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        Room room = roomRepository.findById(roomId).orElseThrow();
        screening.setMovie(movie);
        screening.setRoom(room);
        return new ResponseEntity<>(screeningRepository.save(screening), HttpStatus.OK);
    }
}
