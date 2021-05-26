package pl.touk.ticketBooking.domain.DataValidation;

import pl.touk.ticketBooking.domain.Guest.Guest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {

    public static boolean validateNameAndSurname(Guest guest){
        return validateName(guest.getName()) && validateSurname(guest.getSurname());
    }

    private static boolean validateName(String name){
        Pattern p = Pattern.compile("[A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20}");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    private static boolean validateSurname(String surname){
        Pattern p = Pattern.compile("[A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20}+([-][A-ZŻŹĆĄŚĘŁÓŃ]{1}[a-zżźćńółęąś]{2,20})*");
        Matcher m = p.matcher(surname);
        return m.matches();
    }
}
