package academy.devdojo.essentials2.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Data
public class AnimePostRequestBody {

    @NotNull(message = "The anime name cannot null")
    @NotEmpty(message = "The anime name cannot empty")
    private String name;
}
