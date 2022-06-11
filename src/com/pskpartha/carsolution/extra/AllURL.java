package com.pskpartha.carsolution.extra;

public class AllURL {

	/***
	 * 
	 * Login URL
	 */
	public static String loginURL(String email, String password) {
		return BaseURL.HTTP + "login.php?EmailAddress=" + email + "&Password="
				+ password;
	}

	/***
	 * 
	 * View NearBy List
	 */

	public static String nearByURL(String UPLat, String UPLng, String query,
			String apiKey) {
		return BaseURL.HTTP + "nearbysearch/json?location=" + UPLat + ","
				+ UPLng + "&rankby=distance&types=" + query
				+ "&sensor=false&key=" + apiKey;
	}

	/***
	 * 
	 * View CityGuide Details
	 */

	public static String cityGuideDetailsURL(String reference, String apiKey) {
		return BaseURL.HTTP + "details/json?reference=" + reference
				+ "&sensor=true&key=" + apiKey;
	}

}
