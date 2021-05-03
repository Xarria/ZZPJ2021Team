package pl.ttpsc.restaurant.errors;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String msg) {
        super(msg);
    }
}
