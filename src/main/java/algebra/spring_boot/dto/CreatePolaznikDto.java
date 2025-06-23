package algebra.spring_boot.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePolaznikDto {
    @NotBlank(message = "Ime ne smije biti prazno")
    @Size(min = 2, max = 50, message = "Ime mora imati između 2 i 50 znakova")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Ime mora sadržavati samo slova")
    private String ime;

    @NotBlank(message = "Prezime ne smije biti prazno")
    @Size(min = 2, max = 50, message = "Prezime mora imati između 2 i 50 znakova")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Prezime mora sadržavati samo slova")
    private String prezime;

    private Long programId;
}