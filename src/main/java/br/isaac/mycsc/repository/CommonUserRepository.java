package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.CommonUser;
import br.isaac.mycsc.model.User;

public interface CommonUserRepository extends CrudRepository<CommonUser, Long> {
    CommonUser findByUser(User user);
}
