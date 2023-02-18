package com.arcada.devops.facebookmaven;

import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;

public class FaceBookList {

	public List<String> getFriendList(String profileUrl) {
		int fbLength = 20;
		List<String> friends = new ArrayList<>();
		// Extremly rudamentary check
		if (profileUrl.contains("https://www.facebook.com/")) {
			// pseudocode not a real fetch as don't want to use facebooks api
			Faker faker = new Faker();
			for (int i = 0; i < fbLength; i++) {
				String name = faker.name().fullName();
				friends.add(name);
			}

			return friends;
		} else {
			return friends;
		}
	}
}
