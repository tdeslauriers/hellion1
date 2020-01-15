package world.deslauriers.hellion.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.hellion.model.Nav;

public interface NavDao {
	
	public List<Nav> findAll();
	public Optional<Nav> findOneNav(String nav);
}
