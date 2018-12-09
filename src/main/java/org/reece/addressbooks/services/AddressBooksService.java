package org.reece.addressbooks.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reece.addressbooks.models.AddressBook;
import org.reece.addressbooks.models.Contact;

public class AddressBooksService {
	private static AddressBooksService addressBooksService;
	public List<AddressBook> addressBooks;

	private AddressBooksService() {
		addressBooks = new ArrayList<AddressBook>();
	}

	public static AddressBooksService getInstance() {
		if (addressBooksService == null)
			addressBooksService = new AddressBooksService();
		return addressBooksService;
	}

	public List<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(List<AddressBook> addressBooks) {
		this.addressBooks = addressBooks;
	}

	/**
	 * addAddressBook: Add an address book
	 * 
	 * @param addressBook: Pas Address Book to be added in AddressBooks Map
	 * @return boolean
	 */
	public boolean addAddressBook(AddressBook addressBook) {
		try {
			if (AddressBooksService.getInstance().getAddressBooks().size() > 0 && AddressBooksService.getInstance().getAddressBooks().contains(addressBook)) {
				
				System.err.println("Error Occurred in adding a address book. Address book already exists.");
				return false;

			} else {
				AddressBooksService.getInstance().addressBooks.add(addressBook);
				return true;

			}
		} catch (Exception e) {
			System.err.println("Error Occurred in addAddressBook | Error = " + e.toString());
			return false;
		}
	}

	/**
	 * getUniqueContactsFromAllAddressBooks: Fetch Unique Contacts across all
	 * Address Books
	 * 
	 * @return Set
	 */
	public Set<Contact> getUniqueContactsFromAllAddressBooks() {
		Set<Contact> uniqueContactsSet = new HashSet<Contact>();
		for (AddressBook ab : AddressBooksService.getInstance().getAddressBooks()) {
			for (Contact c : ab.getContacts()) {
				uniqueContactsSet.add(c);
			}
		}
		return uniqueContactsSet;
	}

	@Override
	public String toString() {
		return "AddressBooksService [addressBooks=" + addressBooks + "]";
	}

}
