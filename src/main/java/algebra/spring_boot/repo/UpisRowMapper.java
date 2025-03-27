package algebra.spring_boot.repo;

import algebra.spring_boot.model.Polaznik;
import algebra.spring_boot.model.ProgramObrazovanja;
import algebra.spring_boot.model.Upis;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpisRowMapper implements RowMapper<Upis> {
    @Override
    public Upis mapRow(ResultSet rs, int rowNum) throws SQLException {
        Upis upis = new Upis();
        upis.setUpisID(rs.getLong("upisID"));

        Polaznik polaznik = new Polaznik();
        polaznik.setPolaznikID(rs.getLong("polaznikID"));
        polaznik.setIme(rs.getString("ime"));
        polaznik.setPrezime(rs.getString("prezime"));

        ProgramObrazovanja program = new ProgramObrazovanja();
        program.setId(rs.getLong("programObrazovanjaID"));
        program.setNaziv(rs.getString("naziv"));
        program.setCsvET(rs.getString("csvET"));

        upis.setPolaznik(polaznik);
        upis.setProgramObrazovanja(program);

        return upis;
    }
}