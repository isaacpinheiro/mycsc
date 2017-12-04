package br.isaac.mycsc.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import br.isaac.mycsc.repository.ChatRepository;
import br.isaac.mycsc.model.Chat;

@RestController
public class ChatController {

    @Autowired
    private ChatRepository repository;

    @RequestMapping(value="/api/chat/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Chat listOne(@PathVariable("id") String id) {
        Chat obj = repository.findOne(Long.parseLong(id));
        return obj;
    }

    @RequestMapping(value="/api/chat", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Iterable<Chat> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/api/chat", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String insert(@RequestBody Chat obj) {
        repository.save(obj);
        return "{\"msg\": \"success\"}";
    }

    @RequestMapping(value="/api/chat/{id}", method=RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable("id") String id) {
        Chat obj = repository.findOne(Long.parseLong(id));
        repository.delete(obj);
        return "{\"msg\": \"success\"}";
    }

}
