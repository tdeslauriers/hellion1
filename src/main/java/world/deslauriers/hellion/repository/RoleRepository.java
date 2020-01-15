package world.deslauriers.hellion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import world.deslauriers.hellion.model.Role;
import world.deslauriers.hellion.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByRole(RoleName role);
}
