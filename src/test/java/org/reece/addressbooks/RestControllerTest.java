package org.reece.addressbooks;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reece.addressbooks.models.ApiResponse;
import org.reece.addressbooks.models.Contact;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

	@org.springframework.boot.web.server.LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/**
	 * testGetAllContactsFromAddressBook: Test that initial data load is working by
	 * validating the response of getAllContactsFromAddressBook API
	 */
	@Test
	public void testGetAllContactsFromAddressBook() {

		try {

			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			ResponseEntity<String> response = restTemplate.exchange(
					createURLWithPort("/api/getAllContactsFromAddressBook/1"), HttpMethod.GET, entity, String.class);

			ObjectMapper mapper = new ObjectMapper();
			ApiResponse responseObj = mapper.readValue(response.getBody(), ApiResponse.class);
			String strData = responseObj.getData().toString().replaceAll("=", ":").replaceAll(" ", "");

			String strExpectedResponse = "[{id:1,name:John,phoneNumber:0378569845},{id:2,name:Marcus,phoneNumber:0390452334}]";

			assertEquals(strData.equals(strExpectedResponse), true);
		} catch (Exception e) {
			System.err.println("Error Occured in testGetAllContactsFromAddressBook | Error = " + e);
			Assert.fail("Exception " + e);

		}
	}

	/**
	 * Add a new Contact to AddressBook 2 and validate that response contain the new
	 * contact
	 */
	@Test
	public void addContactInAddressBook() {
		try {

			Contact contact = new Contact();
			contact = new Contact();
			contact.setName("Victoria");
			contact.setPhoneNumber("042908891");

			HttpEntity<Contact> entity = new HttpEntity<Contact>(contact, headers);

			ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/addContactInAddressBook/2"),
					HttpMethod.POST, entity, String.class);

			ObjectMapper mapper = new ObjectMapper();
			ApiResponse responseObj = mapper.readValue(response.getBody(), ApiResponse.class);
			String strData = responseObj.getData().toString().replaceAll("=", ":").replaceAll(" ", "");

			String strNewContact = "{id:3,name:Victoria,phoneNumber:042908891}";

			assertEquals(strData.contains(strNewContact), true);

		} catch (Exception e) {
			System.err.println("Error Occured in addContactInAddressBook | Error = " + e);
			Assert.fail("Exception " + e);
		}

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}