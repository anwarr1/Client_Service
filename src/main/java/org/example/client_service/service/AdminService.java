package org.example.client_service.service;

import org.example.client_service.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public AdminRepository adminRepository;

public Boolean  createAgent(){return true;};
public String generateUniqueId(){return "";};
public String generateAgentPassword(){return "";};
    public Boolean  deleteAgent(){return true;};

}
