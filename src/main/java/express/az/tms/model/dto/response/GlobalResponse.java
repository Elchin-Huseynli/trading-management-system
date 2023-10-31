package express.az.tms.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class GlobalResponse<T> {
    private String message;

    private HttpStatus status;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now().withNano(0);

    T data;


    public static <T> GlobalResponse<T> of(String message, HttpStatus status, T data) {
        return build(message, status, data);
    }

    public static <T> GlobalResponse<String> of(String message, HttpStatus status) {
        return build(message, status, status.name());
    }

    public static <T> GlobalResponse<T> of(String message, T data) {
        return build(message, HttpStatus.OK, data);
    }

    public static <T> GlobalResponse<T> build(String message, HttpStatus status, T data) {
        return GlobalResponse.<T>builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
    }

}
