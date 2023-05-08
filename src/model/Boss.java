package model;

public class Boss {
	private double salary;

    public Boss(int employeeId, String name, String phone, String email, String address, String passcode, double salary) {
        super();
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

