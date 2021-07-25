package pl.touk.ticketBooking.domain.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.touk.ticketBooking.domain.screening.Screening;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "movies")
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NonNull
    private String title;
    @OneToMany(mappedBy = "movie", targetEntity = Screening.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"movie"})
    private List<Screening> screenings = new ArrayList<>();

}
