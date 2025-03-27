package algebra.spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Upis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upisID;

    @NotNull(message = "Polaznik ne smije biti prazan")
    @ManyToOne
    @JoinColumn(name = "polaznik_id") // Matches DB column
    private Polaznik polaznik;

    @ManyToOne
    @JoinColumn(name = "program_obrazovanja_id") // Matches DB column
    private ProgramObrazovanja programObrazovanja;
}
