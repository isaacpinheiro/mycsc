package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.isaac.mycsc.model.EnterpriseUser;
import br.isaac.mycsc.model.User;

public interface EnterpriseUserRepository extends CrudRepository<EnterpriseUser, Long> {

    EnterpriseUser findByUser(User user);

    @Query("select e from EnterpriseUser e where e.tradeName like %:tradeName%")
    Iterable<EnterpriseUser> findByTradeNameLike(@Param("tradeName") String tradeName);

}
