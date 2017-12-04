package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
