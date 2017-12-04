package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.CommonUser;

public interface CommonUserRepository extends CrudRepository<CommonUser, Long> {

}
