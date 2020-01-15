package world.deslauriers.hellion.service;

import java.util.List;
import java.util.Optional;

import world.deslauriers.hellion.model.Nav;

public interface NavService {

	public List<Nav> findAll();
	public Optional<Nav> findOneNav(String nav);
}
