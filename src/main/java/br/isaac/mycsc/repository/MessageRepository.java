package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.Message;
import br.isaac.mycsc.model.EnterpriseUser;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Iterable<Message> findByEnterpriseUser(EnterpriseUser enterpriseUser);
}
