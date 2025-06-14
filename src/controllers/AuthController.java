package controllers;

import models.User;
import models.abstracts.AUser;
import services.AuthService;

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * @return зарегистрированный пользователь
     */
    public AUser register(String name, String phone, String password) {
        try {
            AUser user = new User(name, phone, password);
            authService.register(user);
            return user;
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка регистрации: " + e.getMessage());
            return null;
        }
    }

    /**
     * @return аутентифицированный пользователь или null
     */
    public AUser login(String phone, String password) {
        try {
            return authService.login(phone, password);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка входа: " + e.getMessage());
            return null;
        }
    }
}
