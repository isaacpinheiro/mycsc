package br.isaac.mycsc.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.CommonUserRepository;
import br.isaac.mycsc.repository.UserRepository;
import br.isaac.mycsc.model.CommonUser;
import br.isaac.mycsc.model.User;

@RestController
public class CommonUserController {

    @Autowired
    private CommonUserRepository repository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/api/commonuser/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody CommonUser listOne(@PathVariable("id") String id) {
        CommonUser obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/commonuser", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<CommonUser> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/commonuser", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody CommonUser obj) {

        Date now = new Date();

        if (obj.getId() == null) {

            User u = userRepository.findByEmail(obj.getUser().getEmail());
            obj.setUser(u);
            obj.setCreatedAt(now);
            obj.setUpdatedAt(now);

        } else {

            obj.setUpdatedAt(now);

        }

        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/commonuser/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        CommonUser obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/commonuser/email/{email}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody CommonUser listOneByEmail(@PathVariable("email") String email) {
        User u = userRepository.findByEmail(email);
        CommonUser obj = repository.findByUser(u);
        return obj;
    }

}
