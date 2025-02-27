package main.util;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomUtils {

    // Formating

    public static <T> String formatList(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public static String formatPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D+", "");
        return String.format("(%s) %s-%s",
                digits.substring(0, 3),
                digits.substring(3, 6),
                digits.substring(6, 10));
    }


    // validating
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D+", "");
        return digits.length() == 10;
    }


    // Validate entities
    public static <T> Optional<T> validateEntity(int id, Function<Integer, Optional<T>> retrievalFunction) {
        Optional<T> entity = retrievalFunction.apply(id);
        if (entity.isEmpty()) {
            System.out.println("Entity not found.");
        }
        return entity;
    }

    // Other helper methods
    public static int calculateAge(LocalDate birthdate) {
        // Calculate the period between the birthdate and today
        Period elapsedTime = Period.between(birthdate, LocalDate.now());
        return elapsedTime.getYears();
    }

}


