package com.example.JibiAppClient;

import com.example.JibiAppClient.service.AgentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgentServiceTest {
    @Autowired
    private AgentService agentService;

    @Test
    public void testVerifyUsernameAndPassword(){
        System.out.println(agentService.agentExists("MARYAM_12","1234"));
    }

    @Test
    public void testByUsername(){
        System.out.println(agentService.findByusername("MARYAM_12"));
    }
}
