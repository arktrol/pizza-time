package models.abstracts;

public abstract class AUser {
    private final String name;
    private final String phone;
    private final String password;

    public AUser(String name, String phone, String password) {
        if (phone == null /* || !phone.matches("\\+?\\d{10,15}") **/) { // todo допилить регулярку
            throw new IllegalArgumentException("Некорректный номер телефона");
        }
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + phone + '\'' +
                '}';
    }
}
