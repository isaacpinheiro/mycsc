package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.Product;
import br.isaac.mycsc.model.EnterpriseUser;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findByEnterpriseUser(EnterpriseUser enterpriseUser);
}
