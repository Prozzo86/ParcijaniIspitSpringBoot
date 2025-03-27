package algebra.spring_boot.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpisDto {

    @NotNull(message = "Polaznik ID ne smije biti prazan")
    private Long polaznikID;

    @NotNull(message = "Program obrazovanja ID ne smije biti prazan")
    private Long programObrazovanjaID;
}
