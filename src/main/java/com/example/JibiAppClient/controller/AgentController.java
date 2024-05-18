package com.example.JibiAppClient.controller;

import com.example.JibiAppClient.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.Boolean.TRUE;

@org.springframework.stereotype.Controller
public class AgentController {
    @Autowired
    private AgentService agentService ;

    @GetMapping("/")
    public String PageAgentAuth(){
        return "agentAuth";
    }

    @GetMapping("/authenticate")

    public  String AgentInfosVerification(@RequestParam String username, @RequestParam String password){
        Boolean AgentExist = agentService.agentExists(username,password);
        System.out.println(AgentExist);
        if (AgentExist){
        return "formAddClient";
        }
        else return "pageErreur";
    }


    @GetMapping("/formc")
    public String ClientRegistration(){
        return "formAddClient";
    }
    @GetMapping("/client")
    public String Clientespace(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber, Model model) {

            // Ajouter les données récupérées à l'objet Model
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("phoneNumber", phoneNumber);

            // Traitement pour ajouter l'agent à la base de données
            // Vous pouvez appeler votre service pour traiter l'ajout de l'agent
            // Redirection vers une page de confirmation ou une autre page après l'ajout
            return "client";
        }

}
