package project.assesment.demo.model;

public class User {
    private String name;
    private String dob;
    private String email;
    private String password;
    private String phone;
    private String gender;
    private String address;

    public User() {}

    public User(String name, String dob, String email, String password, String phone, String gender, String address) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }

    public User(String name, String name1, int age) {
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
