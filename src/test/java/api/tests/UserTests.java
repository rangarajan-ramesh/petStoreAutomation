package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeTest
	public void setupData() {
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setUsername(faker.name().username());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		logger = LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
public void testpostUser() {
		logger.info("User starts to create");
		Response response = userEndpoint.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),  200);
		logger.info("User Created");
	
}
	@Test(priority=2)
	public void testgetUser() {
		logger.info("Get User");
		Response response = userEndpoint.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),  200);
	logger.info("Got the users");
}
	
	@Test(priority=3)
	public void testupdateUser() {
		logger.info("Updatig the user");
		
		userpayload.setFirstName(faker.name().firstName());
		Response responseafterupdate = userEndpoint.updateUser(this.userpayload.getUsername(), userpayload);
		responseafterupdate.then().log().all();
		Assert.assertEquals(responseafterupdate.getStatusCode(),  200);
		logger.info("User got updated");
	
}
	
	@Test(priority=4)
	public void testdeleteUser() {
		logger.info("Deleting the user");
		Response response = userEndpoint.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),  200);
	logger.info("User got deleted");
}
}
