package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUsr(String userid, String username, String fName, String lName, String email, String password, String phone) {
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(username);
		userpayload.setFirstName(fName);
		userpayload.setLastName(lName);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		Response res = userEndpoint.createUser(userpayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	@Test(priority=2, dataProvider="username",dataProviderClass=DataProviders.class)
	public void testdeleteUsr(String username) {
		
		Response res = userEndpoint.deleteUser(username);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
}
