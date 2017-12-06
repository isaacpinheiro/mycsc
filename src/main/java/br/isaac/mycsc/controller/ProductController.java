package br.isaac.mycsc.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.ProductRepository;
import br.isaac.mycsc.repository.UserRepository;
import br.isaac.mycsc.repository.EnterpriseUserRepository;
import br.isaac.mycsc.model.Product;
import br.isaac.mycsc.model.User;
import br.isaac.mycsc.model.EnterpriseUser;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnterpriseUserRepository enterpriseUserRepository;

    @RequestMapping(value="/api/product/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Product listOne(@PathVariable("id") String id) {
        Product obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/product", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<Product> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/product", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody Product obj) {

        Date now = new Date();

        User u = userRepository.findByEmail(obj.getEnterpriseUser().getUser().getEmail());
        EnterpriseUser e = enterpriseUserRepository.findByUser(u);
        obj.setEnterpriseUser(e);
        obj.setCreatedAt(now);
        obj.setUpdatedAt(now);

        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/product/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        Product obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/product/email/{email}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<Product> listByEnterpriseUser(@PathVariable("email") String email) {
        User u = userRepository.findByEmail(email);
        EnterpriseUser e = enterpriseUserRepository.findByUser(u);
        Iterable<Product> obj = repository.findByEnterpriseUser(e);
        return obj;
    }

}
