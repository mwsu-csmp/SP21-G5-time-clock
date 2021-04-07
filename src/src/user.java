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
}
