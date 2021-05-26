package pl.touk.ticketBooking.domain.Timetable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value = "SELECT * FROM timetables WHERE session_date = ?1 AND session_time > ?2", nativeQuery = true)
    List<Timetable> findByDay(@Param("day") LocalDate day,@Param("time") LocalTime time);
}
