package pl.touk.ticketBooking.domain.dataValidation;

import pl.touk.ticketBooking.domain.guest.Guest;
import pl.touk.ticketBooking.domain.screening.Screening;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.touk.ticketBooking.domain.seat.Seat.reservingSeatsRules;

public class ValidateInput {

    private static final Pattern namePattern = Pattern.compile("[A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20}");
    private static final Pattern surnamePattern = Pattern.compile("[A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20}+([-][A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20})*");

    public static boolean validateNameAndSurname(Guest guest){
        return validateName(guest.getName()) && validateSurname(guest.getSurname());
    }

    public static boolean validateName(String name){
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    public static boolean validateSurname(String surname){
        Matcher surnameMatcher = surnamePattern.matcher(surname);
        return surnameMatcher.matches();
    }

    public static boolean validateReservationTime(LocalTime sessionTime, LocalDate sessionDate){
        return ( (sessionDate.equals(LocalDate.now()) && LocalTime.now().isBefore(sessionTime.minusMinutes(15))) || LocalDate.now().isBefore(sessionDate) );
    }

    public static boolean isReservationValid(LocalTime sessionTime, LocalDate sessionDate, Guest guest, Screening screening){
       return validateReservationTime(sessionTime, sessionDate)
                && !guest.getTickets().isEmpty()
                && reservingSeatsRules(guest.getTickets(), screening.getTickets(), screening.getRoom().getSeats())
                && validateNameAndSurname(guest);
    }
}
