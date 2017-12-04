package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {

}
