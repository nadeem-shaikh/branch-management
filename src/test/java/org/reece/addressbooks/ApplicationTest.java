package org.reece.addressbooks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.reece.addressbooks.models.AddressBook;
import org.reece.addressbooks.models.Contact;
import org.reece.addressbooks.services.AddressBooksService;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ApplicationTest {

	private AddressBook addressBook;
	private Contact contact;

	String[] addressBookNames = { "HOME", "OFFICE" };
	String[] names = { "John", "Marcus", "Susan", "Henry" };
	String[] phoneNumbers = { "03 7856 9845", "03 9045 2334", "04 6733 3470", "02 7890 8900" };

	/**
	 * loadTestData : This Method loads initial test data in Address Books
	 */
	@Before
	public void loadTestData() {

		Contact contact;
		AddressBook addressBook;

		addressBook = new AddressBook(1, addressBookNames[0]);
		contact = new Contact();
		contact.setName(names[0]);
		contact.setPhoneNumber(phoneNumbers[0]);
		addressBook.addContact(contact);

		contact = new Contact();
		contact.setName(names[1]);
		contact.setPhoneNumber(phoneNumbers[1]);
		addressBook.addContact(contact);

		AddressBooksService.getInstance().addAddressBook(addressBook);
		AddressBooksService.getInstance().addressBooks.add(addressBook);

		addressBook = new AddressBook(2, addressBookNames[1]);
		contact = new Contact();
		contact.setName(names[2]);
		contact.setPhoneNumber(phoneNumbers[2]);
		addressBook.addContact(contact);

		contact = new Contact();
		contact.setName(names[3]);
		contact.setPhoneNumber(phoneNumbers[3]);
		addressBook.addContact(contact);

		AddressBooksService.getInstance().addAddressBook(addressBook);
		AddressBooksService.getInstance().addressBooks.add(addressBook);

	}

	/**
	 * validateContact: This test is to check that contact in Address Book has name
	 * and PhoneNumber
	 * 
	 * @throws Exception
	 */
	@Test
	public void validateContact() {

		try {

			// Get the first Address Book 1 from the Address Book Map
			addressBook = AddressBooksService.getInstance().addressBooks.get(0);

			// Check if Name of 1st Contact is not Empty
			assertEquals(addressBook.getContacts().get(0).getName().isEmpty(), false);

			// Check if Phone Number of 1st Contact is not Empty
			assertEquals(addressBook.getContacts().get(0).getPhoneNumber().isEmpty(), false);
		} catch (Exception e) {
			System.err.println("Error Occurred in validateContact | Error = " + e.toString());
			Assert.fail("Exception " + e);
		}

	}

	/**
	 * addContactInAddressBook: This test is to check the Add Contact Operation
	 */
	@Test
	public void addContactInAddressBook() {
		try {

			AddressBook addressBook = new AddressBook();

			// Get the first Address Book 1 from the Address Book Map
			addressBook = AddressBooksService.getInstance().getAddressBooks().get(0);

			contact = new Contact();
			contact.setName("Nadeem");
			contact.setPhoneNumber("0478778401");

			// Add Contact in Address Book 1
			assertEquals(addressBook.addContact(contact), true);

			// Check if the contact added in above statement exists in the Address Book
			assertEquals(AddressBooksService.getInstance().getAddressBooks().get(0).getContacts().contains(contact),
					true);
		} catch (Exception e) {
			System.err.println("Error Occurred in addContactInAddressBook | Error = " + e.toString());
			Assert.fail("Exception " + e);
		}
	}

	/**
	 * removeContactFromAddressBook : This test is to check Remove Contact Operation
	 */
	@Test
	public void removeContactFromAddressBook() {

		try {

			List<AddressBook> addressBooks = AddressBooksService.getInstance().getAddressBooks();

			AddressBook addressBook = new AddressBook();

			// Get the first Address Book 1 from the Address Book Map
			addressBook = AddressBooksService.getInstance().getAddressBooks().get(0);

			// get the 1st Contact from the Contacts List of 1st Address Book
			Contact contact = addressBook.getContacts().get(0);

			// Check that removeContact is successful
			assertEquals(addressBook.removeContact(contact.getId()), true);

			// Check that the contact removed in the above statement no longer exists in
			// Address Book
			assertEquals(AddressBooksService.getInstance().addressBooks.get(0).getContacts().contains(contact), false);

		} catch (Exception e) {
			System.err.println("Error Occurred in removeContactFromAddressBook | Error = " + e.toString());
			Assert.fail("Exception " + e);
		}
	}

	/**
	 * getAllContactsFromAddressBook : This Test to check successful retrieval of
	 * all Contacts of a given AddressBook
	 */
	@Test
	public void getAllContactsFromAddressBook() {
		try {

			// Fetch All Contacts of the 1st Address Book
			List<Contact> contactList = AddressBooksService.getInstance().addressBooks.get(0).getContacts();

			// Check if the contact list has more than 0 contacts
			assertEquals(contactList.size() > 0, true);
		} catch (Exception e) {
			System.err.println("Error Occurred in getAllContactsFromAddressBook | Error = " + e.toString());
			Assert.fail("Exception " + e);
		}
	}

	/**
	 * getUniqueContactsFromAllAddressBooks: This test case is to check if there is
	 * any duplicate contacts present
	 * 
	 */
	@Test
	public void getUniqueContactsFromAllAddressBooks() {

		try {

			List<Contact> contactList = new ArrayList<Contact>();

			// Fetch all unique contacts from all Address Books and add them in a List
			contactList.addAll(AddressBooksService.getInstance().getUniqueContactsFromAllAddressBooks());

			// Call findDuplicateContact to find out if contactList contains Duplicate
			// Contacts
			assertEquals(findDuplicateContact(contactList), false);
		} catch (Exception e) {
			System.err.println("Error Occurred in getUniqueContactsFromAllAddressBooks | Error = " + e.toString());
			Assert.fail("Exception " + e);
		}

	}

	/**
	 * findDuplicateContact: Checks if Duplicate Entry is present in List.
	 * 
	 * @param contactList: List of Contacts for which duplicate entries need to be
	 *        checked
	 * @return boolean : Returns true if duplicate contact exists
	 */
	public boolean findDuplicateContact(List<Contact> contactList) {

		try {
			for (Integer j = 0; j < contactList.size(); j++) {
				for (Integer k = j + 1; k < contactList.size(); k++) {
					// Check if duplicate contact exists
					if (contactList.get(j).equals(contactList.get(k))) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.err.println("Error Occurred in findDuplicateContact | Error = " + e.toString());
			return false;
		}
	}
}