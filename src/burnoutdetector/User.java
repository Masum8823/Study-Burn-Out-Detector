package burnoutdetector;

class User extends Person {
    private String password; 

    public User(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
}
