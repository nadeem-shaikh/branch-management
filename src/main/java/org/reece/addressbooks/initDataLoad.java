package org.reece.addressbooks;

import org.reece.addressbooks.models.AddressBook;
import org.reece.addressbooks.models.Contact;
import org.reece.addressbooks.services.AddressBooksService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class initDataLoad {

	String[] addressBookNames = { "HOME", "OFFICE" };
	String[] names = { "John", "Marcus", "Susan", "Henry" };
	String[] phoneNumbers = { "03 7856 9845", "03 9045 2334", "04 6733 3470", "02 7890 8900" };

	@EventListener(ApplicationReadyEvent.class)
	public void loadDataAfterStartup()  {

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

		addressBook = null;
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
		//AddressBooksService.getInstance().addressBooks.add(addressBook);

	}

}
