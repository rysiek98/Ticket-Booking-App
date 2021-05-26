package pl.touk.ticketBooking.domain.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.touk.ticketBooking.domain.Timetable.Timetable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
