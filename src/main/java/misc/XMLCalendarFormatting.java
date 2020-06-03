package misc;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class XMLCalendarFormatting {

    public static void main(String[] args) throws DatatypeConfigurationException {

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar("2020-06-02");

        LocalDate localDate = LocalDate.of(
                xmlGregorianCalendar.getYear(),
                xmlGregorianCalendar.getMonth(),
                xmlGregorianCalendar.getDay());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyy", Locale.ENGLISH);
        System.out.println(localDate.format(formatter).toUpperCase());
    }
}
