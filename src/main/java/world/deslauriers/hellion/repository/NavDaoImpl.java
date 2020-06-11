package world.deslauriers.hellion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import world.deslauriers.hellion.model.Nav;

@Repository
public class NavDaoImpl implements NavDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Nav> findAll() {
		
		String sql = "SELECT id, nav, category FROM nav";
		
		return jdbcTemplate.query(sql, 
				(resultSet, rowNumber) -> {
					
					Nav nav = new Nav();
					nav.setId(resultSet.getInt("id"));
					nav.setNav(resultSet.getString("nav"));
					nav.setCategory(resultSet.getString("category"));
					
					return nav;
		});
	}

	@Override
	public Optional<Nav> findOneNav(String nav){
		
		String sql = "SELECT id, nav, category FROM nav WHERE nav = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {nav},
					(resultSet, rowNumber) -> 
						Optional.of(new Nav(
								resultSet.getInt("id"),
								resultSet.getString("nav"),
								resultSet.getString("category")
						)
					)
		);
	}
}
