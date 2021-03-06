package pl.touk.ticketBooking.domain.seat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.touk.ticketBooking.domain.room.Room;
import pl.touk.ticketBooking.domain.ticket.Ticket;
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

    private static final int FIRST_TICKET = 0;
    private static final int SECOND_SEAT = 2;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NotNull
    private int seatNumber;
    @ManyToOne
    @JoinColumn(name="room_id")
    @JsonIgnoreProperties(value = {"seats"})
    private Room room;

    public static boolean reservingSeatsRules(List<Ticket> ticketsToAdd,List<Ticket> existingTickets, List<Seat> seats){
        return checkSeatsNumber(ticketsToAdd,  seats) && doNotReserveBookedSeats(ticketsToAdd, existingTickets)
                && doNotMissSeat(ticketsToAdd) && doNotLeftOneSeat(ticketsToAdd, existingTickets, seats);
    }

    private static boolean doNotMissSeat(List<Ticket> ticketList){
        if (ticketList.size() == 1){
            return true;
        }
        int lastSeat = ticketList.get(FIRST_TICKET).getSeatNumber();
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
        existingTickets.sort(Comparator.comparingInt(Ticket::getSeatNumber));
        if(isOneSeatLeft(existingTickets, seats))
            return false;

        return doNotMissSeat(existingTickets);
    }

    private static boolean isOneSeatLeft(List<Ticket> existingTickets, List<Seat> seats) {
        return (existingTickets.get(FIRST_TICKET).getSeatNumber() == SECOND_SEAT
                || existingTickets.get(existingTickets.size()-1).getSeatNumber() == countPenultimateSeatNumber(seats));
    }

    private static int countPenultimateSeatNumber(List<Seat> seats) {
        return seats.get(seats.size()-1).getSeatNumber() - 1;
    }

}
