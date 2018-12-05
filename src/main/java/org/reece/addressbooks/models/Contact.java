package org.reece.addressbooks.models;

public class Contact {
	private int id;
	private String name;
	private String phoneNumber;

	public Contact() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Contact)) {
			return false;
		}
		Contact c = (Contact) obj; 
		return (c.name.equals(this.name) && (c.phoneNumber.equals(this.phoneNumber)));
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() * this.phoneNumber.hashCode();
	}

	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name =" + name + ", Phone Number=" + phoneNumber + "]";
	}

}
