package model;

public class Doorman extends Employee {
	private double hourlyRate;

    public Doorman(int employeeId, String name, String phone, String email, String address, String passcode, double hourlyRate) {
        super(employeeId, name, phone, email, address, passcode, employeeType);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}

