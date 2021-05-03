package pl.ttpsc.restaurant.errors;

public class OrderStatusDoneException extends RuntimeException {
    public OrderStatusDoneException(String message) {
        super(message);
    }
}
