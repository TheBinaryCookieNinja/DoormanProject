package model;

public class Boss extends Employee {
	private double salary;

    public Boss(int employeeId, String f_name, String l_name, String phone, String email, int addressId, String passcode, double salary) {
        super(employeeId, f_name, l_name, phone, email, addressId, passcode);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

