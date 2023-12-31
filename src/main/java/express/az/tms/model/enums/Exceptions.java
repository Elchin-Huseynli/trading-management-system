package express.az.tms.model.enums;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public enum Exceptions {
    PRODUCT_NOT_FOUND("Product not found!", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("Category not found!", HttpStatus.NOT_FOUND),
    SUPPLIER_NOT_FOUND("Supplier not found!", HttpStatus.NOT_FOUND),
    TOKEN_IS_INVALID_EXCEPTION("Token is invalid!", HttpStatus.BAD_REQUEST),
    USER_IS_ALREADY_EXISTS("User is already exists!", HttpStatus.ALREADY_REPORTED),
    TOKEN_IS_UNREACHABLE("Token is unreachable!", HttpStatus.NOT_FOUND);


    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    Exceptions(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now().withNano(0);
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message: " + message +
                "\nStatus: " + status +
                "\nTimestamp: " + timestamp;
    }
}
