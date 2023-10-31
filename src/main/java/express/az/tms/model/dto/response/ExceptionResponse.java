package express.az.tms.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String message;

    private HttpStatus status;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now().withNano(0);

    public static ExceptionResponse of(String message, HttpStatus status) {
        return ExceptionResponse.builder()
                .message(message)
                .status(status)
                .build();
    }

}
