package algebra.spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUpisDto {

    private Long upisID;

    @NotNull(message = "Polaznik ID ne smije biti prazan")
    private Long polaznikID;

    @NotNull(message = "Program obrazovanja ID ne smije biti prazan")
    private Long programObrazovanjaID;
}
