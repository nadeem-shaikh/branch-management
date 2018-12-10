package org.reece.addressbooks.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.reece.addressbooks.models.AddressBook;
import org.reece.addressbooks.models.ApiResponse;
import org.reece.addressbooks.models.Contact;
import org.reece.addressbooks.services.AddressBooksService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AddressBooksController {

	/**
	 * api/addContactInAddressBook/ : Adds Contact in Address Book
	 * 
	 * @param addressBookId : Pass id of the Address Book to which contact needs to
	 *                      be added
	 * @param contact       : Pass contact details viz name and phoneNumber is JSON
	 *                      format
	 * @return apiResponse : JSON element data contains Payload
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/addContactInAddressBook/{addressBookId}")
	@ResponseBody
	public ApiResponse addContactInAddressBook(@PathVariable Integer addressBookId, @RequestBody Contact contact) {

		ApiResponse apiResponse = new ApiResponse();
		try {
			if (contact.getName() != null && contact.getPhoneNumber() != null) {
				if (!(contact.getName().isEmpty() || contact.getPhoneNumber().isEmpty())) {
					if (AddressBooksService.getInstance().addContact(addressBookId, contact)) {
						apiResponse = new ApiResponse(AddressBooksService.getInstance().getAddressBooks());
						apiResponse.setStatus(HttpStatus.CREATED);
						return apiResponse;
					}
					else
						throw new Exception("Error in Adding Contact to Address Book");
				} else
					// Throw Exception when Contact Name or Phone Number are empty
					throw new Exception("Contact Name and Phone Number must not empty");
			} else
				// Throw Exception when Contact Name or Phone Number are null
				throw new Exception("Contact Name and Phone Number must exist");

		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			apiResponse.setMessage(e.toString());
			return apiResponse;
		}
	}

	/**
	 * /api/removeContactFromAddressBook/ : Removes Contact from the Address Book
	 * 
	 * @param addressBookId : Pass the id of the Address Book for which contacts are
	 *                      needed
	 * @param contactId     : Pass the id of the Contact which needs to be removed
	 * @return apiResponse : JSON element data contains Payload
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/api/removeContactFromAddressBook/{addressBookId}/{contactId}")
	@ResponseBody
	public ApiResponse removeAddressBook(@PathVariable Integer addressBookId, @PathVariable Integer contactId) {

		ApiResponse apiResponse = new ApiResponse();
		try {
			AddressBooksService.getInstance().removeContact(addressBookId, contactId);
			// Throw Exception when no Address Book is Found
			throw new Exception("Address Book Does Not Exist");

		} catch (Exception e) {
			e.printStackTrace();
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			apiResponse.setMessage(e.toString());
			return apiResponse;
		}
	}

	/**
	 * /api/getAllContactsFromAddressBook/ : Returns list of Contacts from Address
	 * Book
	 * 
	 * @param addressBookId : Pass the id of the Address Book for which contacts are
	 *                      needed
	 * @return apiResponse : JSON element data contains Payload
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/getAllContactsFromAddressBook/{addressBookId}")
	@ResponseBody
	public ApiResponse getAllAddressBook(@PathVariable Integer addressBookId) {
		ApiResponse apiResponse = new ApiResponse();
		try {

			List<AddressBook> addressBooks = AddressBooksService.getInstance().getAddressBooks();
			AddressBook addressBook = null;
			List<Contact> contactList = new ArrayList<Contact>();

			// Iterate All Address Books
			for (AddressBook ab : addressBooks) {

				// Find Address Book with given addressBookId
				if (ab.getId() == addressBookId)
					addressBook = ab;
			}

			if (addressBook == null) {
				// Throw Exception when Address Book Does Not Exist
				throw new Exception("Address Book Does Not Exist");

			}
			contactList = addressBook.getContacts();
			apiResponse = new ApiResponse(contactList);
			return apiResponse;
		} catch (Exception e) {
			System.err.println("Error Occured in /api/getAllContactsFromAddressBook/ | Error = " + e.toString());
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			apiResponse.setMessage(e.toString());
			return apiResponse;
		}

	}

	/**
	 * /api/getUniqueContactsFromAllAddressBooks : Returns list of unique Contacts
	 * across multiple Address Books
	 * 
	 * @return apiResponse : JSON element data contains Payload
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/getUniqueContactsFromAllAddressBooks")
	@ResponseBody
	public ApiResponse getUniqueContactsFromAllAddressBooks() {
		ApiResponse apiResponse = new ApiResponse();
		try {
			List<Contact> contactList = new ArrayList<Contact>();
			contactList.addAll(AddressBooksService.getInstance().getUniqueContactsFromAllAddressBooks());
			apiResponse = new ApiResponse(contactList);
			return apiResponse;
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			apiResponse.setMessage(e.toString());
			return apiResponse;
		}
	}
}