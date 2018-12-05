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

	/**
	 * addContact: add contact to the address book
	 * @param contact : Pass the Contact Object which needs to be added in Contacts
	 *                List
	 * @return boolean
	 */
	public boolean addContact(Contact contact) {
		try {
			//Check if Contact Already Exists in Address Book
			if (! this.getContacts().contains(contact)) {
				this.length ++ ;
				contact.setId(this.length);				
				this.contacts.add(contact);
				return true;
			} else {
				System.err.println("Error Occurred in adding a contact to address book. Contact already exists");
				return false;
			}
		} catch (Exception e) {
			System.err.println("Error Occurred in addContact | Error = " + e.toString());
		}
		return false;
	}

	/**
	 * removeContact: Remove contact from the Contacts List
	 * @param contactId : Pass the Integer contactId of the contact which needs to
	 *                  be removed from the list
	 * @return boolean
	 */
	public boolean removeContact(Integer contactId) {
		try {

			for (Contact c : contacts) {
				if (c.getId() == contactId) {
					this.contacts.remove(c);
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error Occurred in removeContact | Error = " + e.toString());
		}
		return false;
	}

	@Override
	public String toString() {
		return "AddressBook [id=" + id + ", name=" + name + ", contacts=" + contacts + "]";
	}
}
