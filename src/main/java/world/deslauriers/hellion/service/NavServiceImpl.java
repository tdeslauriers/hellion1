package world.deslauriers.hellion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import world.deslauriers.hellion.model.Nav;
import world.deslauriers.hellion.repository.NavDao;

@Service
public class NavServiceImpl implements NavService {
	
	@Autowired
	private NavDao navDao;
	
	@Override
	public List<Nav> findAll() {
		
		return navDao.findAll();
	}

	@Override
	public Optional<Nav> findOneNav(String nav) {
		
		return navDao.findOneNav(nav);
	}

}
