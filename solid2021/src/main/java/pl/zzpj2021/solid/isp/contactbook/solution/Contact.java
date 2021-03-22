package pl.zzpj2021.solid.isp.contactbook.solution;

class Contact {
	public String name;
	public String address;
	
	public Contact(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}



