package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.EnterpriseUser;
import br.isaac.mycsc.model.User;

public interface EnterpriseUserRepository extends CrudRepository<EnterpriseUser, Long> {
    EnterpriseUser findByUser(User user);
}
