package main.util;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomUtil {

    // Format List
    public static <T> String formatList(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    // Validate entities
    public static <T> Optional<T> validateEntity(int id, Function<Integer, Optional<T>> retrievalFunction) {
        Optional<T> entity = retrievalFunction.apply(id);
        if (entity.isEmpty()) {
            System.out.println("Entity not found.");
        }
        return entity;
    }
}


