
package pl.touk.ticketBooking.domain.Room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Timetable.Timetable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String roomName;
    private int numberOfSeats;

    @OneToMany(mappedBy = "room", targetEntity = Timetable.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("room")
    private Set<Timetable> timetables = new HashSet<Timetable>();
}
