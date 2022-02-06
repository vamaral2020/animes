package academy.devdojo.essentials2.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePutRequestBody {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
}
