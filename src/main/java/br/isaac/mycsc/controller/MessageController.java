package br.isaac.mycsc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.MessageRepository;
import br.isaac.mycsc.model.Message;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @RequestMapping(value="/api/message/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Message listOne(@PathVariable("id") String id) {
        Message obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/message", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<Message> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/message", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody Message obj) {
        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/message/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        Message obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

}
