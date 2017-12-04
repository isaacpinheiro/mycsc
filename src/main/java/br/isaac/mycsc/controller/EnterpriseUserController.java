package br.isaac.mycsc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.EnterpriseUserRepository;
import br.isaac.mycsc.model.EnterpriseUser;

@RestController
public class EnterpriseUserController {

    @Autowired
    private EnterpriseUserRepository repository;

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
