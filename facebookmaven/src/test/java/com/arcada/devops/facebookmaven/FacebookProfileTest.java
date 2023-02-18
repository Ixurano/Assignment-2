package com.arcada.devops.facebookmaven;

import junit.framework.TestCase;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;

public class FacebookProfileTest extends TestCase {
	@Test
	public void testGetFriendList(){
		FaceBookList facebooklist = new FaceBookList();

		List<String> friendList = facebooklist.getFriendList("https://www.facebook.com/profile");

		assertNotNull("The returned friend list is null", friendList);
		assertEquals(20, friendList.size());

		// Checks if name is to long can be useful in other projects and ensure no
		// script injection is done
		for (String friend : friendList) {
			assertTrue("Name is too long" + friend, friend.length() <= 35);
			// Checks for special characters
			assertFalse("Friend name contains special character: " + friend, friend.matches("[^a-zA-Z0-9. ]+"));
			// Reverted function to test that the RegEx works as intended for the data
			// fetched
			// assertTrue("Friend name does not contains special character: " + friend,
			// friend.matches("[^a-zA-Z0-9. ]+"));
		}

	}

	@Test
	public void testGetFriendListReturnsEmptyList() {
		FaceBookList facebooklist = new FaceBookList();
		List<String> friendList = facebooklist.getFriendList("invalid_profile_url");

		assertNotNull("The returned friend list is null", friendList);
		assertTrue("The friend list is not empty", friendList.isEmpty());
	}
	
	@Test
    public void testGetFriendListPerformance() {
		FaceBookList facebooklist = new FaceBookList();
        long startTime = System.currentTimeMillis();
        List<String> friendList = facebooklist.getFriendList("https://www.facebook.com/profile");
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        assertNotNull("The returned friend list is null", friendList);
        assertTrue("The friend list contains items", friendList.size() > 0);
        //Checks exectution time. a API call can be used to check performance if there is any loading errors from the backend that takes to long
        assertTrue("The method took too long to execute", elapsedTime < 1000);
    }
}
