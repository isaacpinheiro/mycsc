package br.isaac.mycsc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.UserRepository;
import br.isaac.mycsc.model.User;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value="/api/user/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody User listOne(@PathVariable("id") String id) {
        User obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/user", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<User> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/user", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody User obj) {
        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/user/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        User obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

}
