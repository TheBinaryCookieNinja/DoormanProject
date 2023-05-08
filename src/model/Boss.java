package model;

public class Boss extends Employee {
	private double salary;

    public Boss(int employeeId, String name, String phone, String email, String address, String passcode, double salary) {
        super(employeeId, name, phone, email, address, passcode, employeeType);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

