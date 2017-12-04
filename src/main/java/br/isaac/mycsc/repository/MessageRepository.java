package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
