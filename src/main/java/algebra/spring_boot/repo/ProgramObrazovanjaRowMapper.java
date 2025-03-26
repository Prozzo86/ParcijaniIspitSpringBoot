package algebra.spring_boot.repo;

import algebra.spring_boot.model.ProgramObrazovanja;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramObrazovanjaRowMapper implements RowMapper<ProgramObrazovanja> {
    @Override
    public ProgramObrazovanja mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProgramObrazovanja program = new ProgramObrazovanja();
        program.setProgramObrazovanjaID(rs.getLong("programObrazovanjaID"));
        program.setNaziv(rs.getString("naziv"));
        program.setCsvET(rs.getString("csvET"));
        return program;
    }
}