package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.EnterpriseUser;

public interface EnterpriseUserRepository extends CrudRepository<EnterpriseUser, Long> {

}
