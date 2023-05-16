package model;

public class Bar {
		private int barId;
		private String name;
	    private String phone;
	    private String email;
	    private int addressId;
	    private String cvr;

	    public Bar(int barId, String name, String phone, String email, int addressId, String cvr) {
	    }

	    public int getBarId() {
	    	return barId;
	    }
	    
	    public void setBarId(int barId) {
	    	this.barId = barId;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
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

	    
	    public int getAddressId() {
	        return addressId;
	    }

	    public void setAddressId(int addressId) {
	        this.addressId = addressId;
	    }

	   
	    public String getCvr() {
	        return cvr;
	    }

	    public void setCvr(String cvr) {
	        this.cvr = cvr;
	    }
	}

