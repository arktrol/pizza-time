package models;

import models.abstracts.AUser;

public class User extends AUser {

    public User(String name, String id, String password) {
        super(name, id, password);
    }

}
