package org.example.client_service.config;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratePassword {
    public static String generateTemporaryPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

}
