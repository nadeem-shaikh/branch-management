package org.reece.addressbooks.models;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	private int id;
	private String name;
	private int length;
	private List<Contact> contacts;

	public AddressBook() {
	}

	public AddressBook(String name) {
		this.name = name;
		this.id = 0;
		this.contacts = new ArrayList<Contact>();
	}

	public AddressBook(int id, String name) {
		this.id = id;
		this.name = name;
		this.length = 0;
		contacts = new ArrayList<Contact>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}