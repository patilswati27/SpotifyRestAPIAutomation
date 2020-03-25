package com.bridgelabz;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Base.TestBase;
import Utilities.Payload;
import Utilities.Utilities;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class SpotifyRestApi extends TestBase {
	@Test
	public void spotify_RestAssured_Test() {
		String res = RestAssured.given().header("Authorization", token).when().get("https://api.spotify.com/v1/me")
				.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		userId = js.get("id");
		System.out.println("User id " + userId);

	}

@Test
public void GivenToken_GetUserProfileTest() {
String res = RestAssured.given()
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .header("Authorization", token)
        .when()
        .get("https://api.spotify.com/v1/users/"+userId)
        .then().assertThat().statusCode(200).extract()
        .response().asString();
        System.out.println(res);
        JsonPath js=Utilities.rawJson(res);
        String type=js.get("type");
 }
//--------------------------Playlist-------------------------------------//
@Test(enabled=false)
public void spotify_RestAssured_CreatePlaylist_Test() {
	String res = RestAssured.given()
			 .header("Accept", "application/json")
	         .header("Content-Type", "application/json")
			 .header("Authorization", token)
			 .body(Payload.createPlaylist())
			 .when()
			 .post("https://api.spotify.com/v1/users/+userId+/playlists")
			 .then()//.assertThat().statusCode(200)
			 .extract().response().asString();
	System.out.println(res);
	JsonPath js = Utilities.rawJson(res);
	String Name = js.get("name");
	System.out.println("Name of created playlist " + Name);

}

@Test
public void spotify_RestAssured_returnTotalPlaylist_Test() {
String res = RestAssured.given()
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .header("Authorization", token)
        .when()
        .get("https://api.spotify.com/v1/me/playlists")
        .then().assertThat().statusCode(200).extract()
        .response().asString();
        System.out.println(res);
        JsonPath js=Utilities.rawJson(res);
        int total=js.getInt("total");
        System.out.println("Total playlist is "+total);
 }

}