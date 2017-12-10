package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.isaac.mycsc.model.Product;
import br.isaac.mycsc.model.EnterpriseUser;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByEnterpriseUser(EnterpriseUser enterpriseUser);

    @Query("select p from Product p where p.name like %:name%")
    Iterable<Product> findByNameLike(@Param("name") String name);

}
