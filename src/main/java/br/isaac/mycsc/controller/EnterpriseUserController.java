package br.isaac.mycsc.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.EnterpriseUserRepository;
import br.isaac.mycsc.repository.UserRepository;
import br.isaac.mycsc.model.EnterpriseUser;
import br.isaac.mycsc.model.User;
import br.isaac.mycsc.util.MyHash;

@RestController
public class EnterpriseUserController {

    @Autowired
    private EnterpriseUserRepository repository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/api/enterpriseuser/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody EnterpriseUser listOne(@PathVariable("id") String id) {
        EnterpriseUser obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/enterpriseuser", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<EnterpriseUser> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/enterpriseuser", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody EnterpriseUser obj) {

        Date now = new Date();

        if (obj.getId() == null) {

            User u = userRepository.findByEmail(obj.getUser().getEmail());

            if (u != null) {
                return "{\"msg\": \"Já existe um usuário cadastrado com esse E-Mail.\"}";
            }

            u = new User();
            u.setEmail(obj.getUser().getEmail());
            u.setCreatedAt(now);
            u.setUpdatedAt(now);
            u.setToken(MyHash.generateToken());
            u.setPassword(MyHash.generate(obj.getUser().getPassword(), u.getToken()));
            userRepository.save(u);

            u = userRepository.findByEmail(obj.getUser().getEmail());
            obj.setUser(u);
            obj.setCreatedAt(now);
            obj.setUpdatedAt(now);

        } else {

            obj.setUpdatedAt(now);

        }

        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/enterpriseuser/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        EnterpriseUser obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

}
