package com.example.JibiAppClient.controller;

import com.example.JibiAppClient.model.Agent;
import com.example.JibiAppClient.model.Client;
import com.example.JibiAppClient.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static java.lang.Boolean.TRUE;

@RestController
@RequestMapping("/api2")
//@org.springframework.stereotype.Controller
public class AgentController {
    @Autowired
    private AgentService agentService ;

    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    /*@GetMapping("/")
    public String PageAgentAuth(){
        return "agentAuth";
    }*/

    //avec @Controller
    /*@GetMapping("/authenticate")

    public  String AgentInfosVerification(@RequestParam String username, @RequestParam String password){
        Boolean AgentExist = agentService.agentExists(username,password);
        System.out.println(AgentExist);
        if (AgentExist){
        return "formAddClient";

        }
        else return "pageErreur";
    }*/

    //Avec RestController
    @GetMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Boolean AgentInfosVerification(@RequestParam String username, @RequestParam String password) {
        Boolean AgentExist = agentService.agentExists(username, password);
        System.out.println(AgentExist);
        return AgentExist;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Agent> getAllAgents(){
        return agentService.getAllAgents();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAgent(@PathVariable String id ){
        agentService.deleteAgent(Long.parseLong(id));
    }
    /*@GetMapping("/formc")
    public String ClientRegistration(){
        return "formAddClient";
    }*/

}
