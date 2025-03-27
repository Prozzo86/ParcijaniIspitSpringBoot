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
public class CreateProgramObrazovanjaDto {

    private Long programObrazovanjaID;

    @NotBlank(message = "Naziv programa ne smije biti prazan")
    @Size(min = 3, max = 100, message = "Naziv programa mora imati između 3 i 100 znakova")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Naziv može sadržavati samo slova, brojeve i razmake")
    private String naziv;

    @NotBlank(message = "CSVET ne smije biti prazan")
    @Size(min = 1, max = 10, message = "CSVET mora imati između 1 i 10 znakova")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "CSVET može sadržavati samo slova i brojeve")
    private String csvET;
}