package pl.touk.ticketBooking.domain.Movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Timetable.Timetable;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NonNull
    private String title;

    @OneToMany(mappedBy = "movie", targetEntity = Timetable.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("movie")
    private List<Timetable> timetables = new ArrayList<>();


}
