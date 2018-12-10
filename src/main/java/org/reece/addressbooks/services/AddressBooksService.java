package org.reece.addressbooks.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reece.addressbooks.models.AddressBook;
import org.reece.addressbooks.models.ApiResponse;
import org.reece.addressbooks.models.Contact;
import org.springframework.http.HttpStatus;

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
			if (AddressBooksService.getInstance().getAddressBooks().size() > 0
					&& AddressBooksService.getInstance().getAddressBooks().contains(addressBook)) {

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
	 * addContact: add contact to the address book
	 * 
	 * @param contact : Pass the Contact Object which needs to be added in Contacts
	 *                List
	 * @return boolean
	 */
	public boolean addContact(Integer addressBookId, Contact contact) {
		try {
			List<AddressBook> addressBooks = AddressBooksService.getInstance().getAddressBooks();
			for (AddressBook ab : addressBooks) {

				// Find Address Book with given AddressBook Id
				if (ab.getId() == addressBookId) {
					if (!ab.getContacts().contains(contact)) {
						ab.setLength(ab.getLength() + 1);
						contact.setId(ab.getLength());
						ab.getContacts().add(contact);
						return true;
					} else {
						System.err.println("Error Occurred in adding a contact to address book. Contact already exists");
						return false;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error Occurred in addContact | Error = " + e.toString());
		}
		return false;
	}

	/**
	 * removeContact: Remove contact from the Contacts List
	 * 
	 * @param contactId : Pass the Integer contactId of the contact which needs to
	 *                  be removed from the list
	 * @return boolean
	 */
	public boolean removeContact(Integer addressBookId, Integer contactId) {
		try {
			List<AddressBook> addressBooks = AddressBooksService.getInstance().getAddressBooks();
			for (AddressBook ab : addressBooks) {

				// Find Address Book with given AddressBook Id
				if (ab.getId() == addressBookId) {
					for (Contact c : ab.getContacts()) {
						if (c.getId() == contactId) {
							ab.getContacts().remove(c);
							return true;
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error Occurred in removeContact | Error = " + e.toString());
		}
		return false;
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