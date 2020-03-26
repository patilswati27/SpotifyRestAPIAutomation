package com.bridgelabz;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Base.Resources;
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
		String res = RestAssured.given()
				.header("Authorization", token)
				.when()
				.get(Resources.spotifyGetData())
				.then().assertThat().statusCode(200).extract().response().asString();
		//System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		userId = js.get("id");
		System.out.println("User id " + userId);

	}

	@Test
	public void GivenToken_GetUserProfile() {
		String res = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get(Resources.getUserProfileData())
				.then()
				.assertThat().statusCode(200).extract().response().asString();
		//System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		String type = js.get("type");
	}

//--------------------------Playlist-------------------------------------//
	@Test
	public void spotify_RestAssured_CreatePlaylist() {
		String res = RestAssured.given().header("Accept", "application/json").header("Content-Type", "application/json")
				.header("Authorization", token)
				.body(Payload.createPlaylist()).when()
				.post(Resources.postCreatePlaylistData()).then()// .assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		String Name = js.get("name");
		//System.out.println("Name of created playlist " + Name);

	}

	@Test
	public void spotify_RestAssured_updatePlaylist() {
		String res = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.body(Payload.updatePlaylist())
				.when()
				.put("https://api.spotify.com/v1/playlists/"+PlaylistId)
				.then()//.assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println("updated result "+res);
//		JsonPath js = Utilities.rawJson(res);
//		String Name = js.get("name");
//		System.out.println("Name of created playlist " + Name);
		

	}
	@Test
	public void spotify_RestAssured_returnTotalPlaylist() {
		String res = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get(Resources.getPlaylistData()).then()
				.assertThat().statusCode(200).extract().response().asString();
		//System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		int total = js.getInt("total");
		System.out.println("Total playlist is " + total);
	}

	@Test
	public void spotify_RestAssured_returnCurrentPlaylistId() {

		String res = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get().then()
				.assertThat().statusCode(200).extract().response().asString();
		//System.out.println(res);
		JsonPath js = Utilities.rawJson(res);
		PlaylistId = js.get("items[0].id");
		System.out.println("playlist id " + PlaylistId);
	}
	
	@Test
	public void spotify_RestAssured_returnTotalTracklist() {

		String res = RestAssured.given()
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+PlaylistId+"/tracks")
				.then()
				.extract().response().asString();
		    JsonPath js=Utilities.rawJson(res);
		    int Tracklist=js.getInt("total");
			System.out.println("Total tracklist "+Tracklist);
	
	}
	 @Test
	    public void givenTrackId_shoudDeleteThisTrack(){
	        Response response = RestAssured.given()
	                .accept("application/json")
	                .contentType("application/json")
	                .header("Authorization", token)
	                .body("{\"tracks\":[{\"uri\":\"spotify:track:2DB2zVP1LVu6jjyrvqD44z\",\"positions\":[0]}]}")
	                .when()
	                .delete("https://api.spotify.com/v1/playlists/"+ PlaylistId +"/tracks")
	                .then().extract().response();
	        Object trackId = response.path("trackId");
	        response.prettyPrint();
	    }
	}
 