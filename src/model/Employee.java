package model;

public abstract class Employee {
	private int employeeId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String passcode;
	private String l_name;
	private String f_name;

    public Employee(String f_name, String l_name, String phone, String email, String address, String passcode) {
        this.employeeId = employeeId;
        this.f_name = f_name;
        this.l_name = l_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.passcode = passcode;
    }

    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    
    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }
    
    public String getL_name() {
    	return l_name;
    }
    
    public void setL_name(String l_name) {
    	this.l_name = l_name;
    }

  
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}

