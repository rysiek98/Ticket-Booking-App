package pl.touk.ticketBooking.domain.Seat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.touk.ticketBooking.domain.Room.Room;
import pl.touk.ticketBooking.domain.Ticket.Ticket;


import javax.persistence.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "seats")
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotNull
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name="room_id")
    @JsonIgnoreProperties("seats")
    private Room room;

    public static boolean reservingSeatsRules(List<Ticket> ticketsToAdd,List<Ticket> existingTickets, List<Seat> seats){
        return checkSeatsNumber(ticketsToAdd,  seats) && doNotReserveBookedSeats(ticketsToAdd, existingTickets)
                && doNotMissSeat(ticketsToAdd) && doNotLeftOneSeat(ticketsToAdd, existingTickets, seats);
    }

    private static boolean doNotMissSeat(List<Ticket> ticketList){
        if (ticketList.size() == 1){
            return true;
        }
        int lastSeat = ticketList.get(0).getSeatNumber();
        for (int i = 1; i < ticketList.size(); i++){
            if(lastSeat +2 == ticketList.get(i).getSeatNumber()){
                return false;
            }
            lastSeat = ticketList.get(i).getSeatNumber();
        }
        return true;
    }

    private static boolean checkSeatsNumber(List<Ticket> tickets, List<Seat> seats){
        if(tickets.stream().anyMatch(ticket -> 0 >= ticket.getSeatNumber()))
            return false;

        Set<Integer> availableSeats = seats.stream().map(Seat::getSeatNumber).collect(Collectors.toSet());
        return !(tickets.stream().anyMatch(ticket -> availableSeats.add(ticket.getSeatNumber())));

    }

    private static boolean doNotReserveBookedSeats(List<Ticket> ticketsToAdd, List<Ticket> existingTicketsDB){
        if(existingTicketsDB.isEmpty()){
            return true;
        }
        Set<Integer> existingTickets = existingTicketsDB.stream().map(Ticket::getSeatNumber).collect(Collectors.toSet());
        return ticketsToAdd.stream().anyMatch(ticket-> existingTickets.add(ticket.getSeatNumber()));
    }

    private static boolean doNotLeftOneSeat(List<Ticket> ticketsToAdd,List<Ticket> existingTickets, List<Seat> seats){
        ticketsToAdd.forEach(ticket -> existingTickets.add(ticket));
        existingTickets.sort(Comparator.comparingInt(t -> t.getSeatNumber()));
        if(existingTickets.get(0).getSeatNumber() == 2
                || existingTickets.get(existingTickets.size()-1).getSeatNumber() == seats.get(seats.size()-1).getSeatNumber() - 1)
            return false;

        return doNotMissSeat(existingTickets);
    }

}
