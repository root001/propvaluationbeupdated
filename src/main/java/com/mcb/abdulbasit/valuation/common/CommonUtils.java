package com.mcb.abdulbasit.valuation.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommonUtils {

    public String convertDate(LocalDateTime inputDate) {
        try {
            // Parse the input date
        //    LocalDateTime dateTime = LocalDateTime.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Define the desired output format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Format the parsed date
            return inputDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
