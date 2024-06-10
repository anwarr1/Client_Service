package org.example.client_service.validator;//package org.example.client_service.validator;
//
//import org.example.client_service.models.User;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UtilisateurValidator {
//
//    public static List<String> validate(User utilisateurDto) {
//        List<String> errors = new ArrayList<>();
//
//        if (utilisateurDto == null) {
//            errors.add("Veuillez renseigner le nom d'utilisateur");
//            errors.add("Veuillez renseigner le prenom d'utilisateur");
//            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
////            errors.add("Veuillez renseigner l'adresse d'utilisateur");
////            errors.addAll(AdresseValidator.validate(null));
//            return errors;
//        }
//
//        if (!StringUtils.hasLength(utilisateurDto.getFirstName())) {
//            errors.add("Veuillez renseigner le nom d'utilisateur");
//        }
//        if (!StringUtils.hasLength(utilisateurDto.getLastName())) {
//            errors.add("Veuillez renseigner le prenom d'utilisateur");
//        }
//        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
//            errors.add("Veuillez renseigner l'email d'utilisateur");
//        }
//        if (!StringUtils.hasLength(utilisateurDto.getPassword())) {
//            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
//        }
////        if (utilisateurDto.getTelephone() == null) {
////            errors.add("Veuillez renseigner le telephone");
////        }
////        errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));
//
//        return errors;
//    }
//}