package algebra.spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Upis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upisID;

    @NotNull(message = "Polaznik ne smije biti prazan")
    @ManyToOne
    @JoinColumn(name = "IDPolaznik")
    private Polaznik polaznik;

    @NotNull(message = "Program obrazovanja ne smije biti prazan")
    @ManyToOne
    @JoinColumn(name = "IDProgramObrazovanja")
    private ProgramObrazovanja programObrazovanja;
}
