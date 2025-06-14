package services;

import models.abstracts.AUser;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, AUser> registeredUsers = new HashMap<>(); // id -> User

    /**
     * @return true, если регистрация успешна
     * @throws IllegalArgumentException если пользователь уже существует
     */
    public boolean register(AUser newUser) {
        if (newUser == null) throw new IllegalArgumentException("User cannot be null");

        String userId = newUser.getPhone();
        if (registeredUsers.containsKey(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " already exists");
        }

        registeredUsers.put(userId, newUser);
        return true;
    }

    public AUser login(String phone, String password) {
        if (phone == null || password == null) {
            throw new IllegalArgumentException("Телефон и пароль обязательны");
        }

        AUser user = registeredUsers.get(phone);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        return user;
    }

    /**
     * Проверяет, зарегистрирован ли пользователь
     */
    public boolean isAuthenticated(String userId) {
        return registeredUsers.containsKey(userId);
    }

    /**
     * Получить пользователя по id (null если не найден)
     */
    public AUser getUserById(String userId) {
        return registeredUsers.get(userId);
    }
}
