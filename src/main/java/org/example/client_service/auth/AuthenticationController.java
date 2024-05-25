package org.example.client_service.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  org.example.client_service.auth.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor

public class AuthenticationController {
  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @GetMapping("/confirm")
  public ResponseEntity<String> confirm(@RequestParam String token) {
    String responseMessage = service.confirmToken(token);
    return ResponseEntity.ok(responseMessage);
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    System.out.println("Ana f controller");
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }}

//  @GetMapping("/id")
//  public Integer getId(@RequestParam("email") String email) {
//    return utilisateurService.findByEmail(email);
//  }

//  @GetMapping("/{id}")
//    public User getUtilisateur(@PathVariable("id") int id) {
//        return utilisateurService.findById(id);
//    }
//}
