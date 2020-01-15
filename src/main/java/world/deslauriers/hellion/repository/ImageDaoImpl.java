package world.deslauriers.hellion.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import world.deslauriers.hellion.model.Image;

@Repository
public class ImageDaoImpl implements ImageDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Image> findByYearId(Integer yearId) {
		
		String sql = "SELECT id, name, description, navcategory "
				+ "FROM image1 "
				+ "WHERE navcategory = ?";
		return jdbcTemplate.query(sql, new Object[] {yearId}, 
				(resultSet, rowNumber) -> 
			
					new Image(
							
							resultSet.getInt("id"),
							resultSet.getString("name"),
							resultSet.getString("description"),
							resultSet.getInt("navcategory")
					)
		);
	}

}
