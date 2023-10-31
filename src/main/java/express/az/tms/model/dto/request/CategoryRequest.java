package express.az.tms.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static express.az.tms.model.constants.Constants.NAME_REGEX;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Validated
public class CategoryRequest {

    @NotBlank
    @Size(min = 3, max = 50, message = NAME_REGEX)
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
