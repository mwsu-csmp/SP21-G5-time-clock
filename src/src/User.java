public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String dob;
    private String password;
    private PaymentMethod preferredPayment = PaymentMethod.CHECK;
    private double dollarsAnHour = 4.00;
    private double hoursWorked = 1.5;

    public User(String name, String username, String email, String address, String phoneNumber, String dob, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }

    public PaymentMethod getPreferredPayment() {
        return preferredPayment;
    }

    public double getDollarsAnHour() {
        return dollarsAnHour;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPreferredPayment(PaymentMethod preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    public void setDollarsAnHour(int dollarsAnHour) {
        this.dollarsAnHour = dollarsAnHour;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getDollarsEarned() {
        return hoursWorked * dollarsAnHour;
    }
}
