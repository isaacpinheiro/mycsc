package br.isaac.mycsc.repository;

import org.springframework.data.repository.CrudRepository;
import br.isaac.mycsc.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
