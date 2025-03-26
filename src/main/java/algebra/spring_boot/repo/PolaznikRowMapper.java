package algebra.spring_boot.repo;

import algebra.spring_boot.model.Polaznik;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolaznikRowMapper implements RowMapper<Polaznik> {
    @Override
    public Polaznik mapRow(ResultSet rs, int rowNum) throws SQLException {
        Polaznik polaznik = new Polaznik();
        polaznik.setPolaznikID(rs.getLong("polaznikID"));
        polaznik.setIme(rs.getString("ime"));
        polaznik.setPrezime(rs.getString("prezime"));
        return polaznik;
    }
}