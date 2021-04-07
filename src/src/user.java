public class user {
    private int id;
    private String name;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String dob;
    private String password;
    private paymentMethod preferredPayment = paymentMethod.CHECK;
    private int dollarsAnHour;
    private int hoursWorked;

    public void user(String name, String username, String email, String address, String phoneNumber, String dob, String password) {
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

    public paymentMethod getPreferredPayment() {
        return preferredPayment;
    }

    public int getDollarsAnHour() {
        return dollarsAnHour;
    }

    public int getHoursWorked() {
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

    public void setPreferredPayment(paymentMethod preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    public void setDollarsAnHour(int dollarsAnHour) {
        this.dollarsAnHour = dollarsAnHour;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
