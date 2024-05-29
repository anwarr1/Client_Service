package org.example.client_service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.client_service.excep.EntityNotFoundException;
import org.example.client_service.excep.ErrorCodes;
import org.example.client_service.models.User;
import org.example.client_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private UserRepository UserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository UserRepository,
                       PasswordEncoder passwordEncoder) {
        this.UserRepository = UserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User dto) {
//        List<String> errors = UtilisateurValidator.validate(dto);
//        if (!errors.isEmpty()) {
//            log.error("Utilisateur is not valid {}", dto);
//            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID,
//                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
//
//        }
//        if (userAlreadyExists(dto.getEmail())) {
//            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
//                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
//        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
//        return UtilisateurDto.fromEntity(
//                UserRepository.save(
//                        UtilisateurDto.toEntity(dto)
//                )
//        );
        return UserRepository.save(dto);

    }

    private boolean userAlreadyExists(String email) {
        Optional<User> utilisateur = UserRepository.findByEmail(email);
        return utilisateur.isPresent();
    }

    public Optional<User> findById(Long id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null;
        }
        return UserRepository.findById(id);

    }

    public List<User> findAll() {
        return UserRepository.findAll();
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }
        UserRepository.deleteById(id);

    }

    public Optional<User> findByEmail(String email) {
        return UserRepository.findByEmail(email);

    }



    public User update(User utilisateur) {
        if (utilisateur == null) {
            log.error("Utilisateur is null");
            return null;
        }
        return UserRepository.save(utilisateur);
    }


    // 111111
    public User changerPassword(String email, String password) {

        Optional<User> userExist = UserRepository.findByEmail(email);
        if (userExist.isEmpty()) {
            log.warn("Aucun User Assoucier a cet Email" + email);
            throw new EntityNotFoundException("Aucun User Assoucier a cet Email" + email, ErrorCodes.UTILISATEUR_NOT_FOUND);
        }

        User utilisateur = userExist.get();
        //changer password pour User si la 1ere fois qu'il se connect
        utilisateur.setPassword(passwordEncoder.encode(password));
        //set la variable Is_first_time to false
        utilisateur.setIs_first_time(false);

        //save the user in bd to be updated
        UserRepository.save(utilisateur);

        return utilisateur ;
    }

}
