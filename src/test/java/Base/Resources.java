package Base;

import java.net.URI;

public class Resources extends TestBase {

	public static String spotifyGetData() {
		String res = "https://api.spotify.com/v1/me";
		return res;
	}

	public static String getUserProfileData() {
		String res = "https://api.spotify.com/v1/users/" + userId;
		return res;
	}

	public static String postCreatePlaylistData() {
		String res = "https://api.spotify.com/v1/users/+userId+/playlists";
		return res;
	}

	public static String getPlaylistData() {
		String res = "https://api.spotify.com/v1/me/playlists";
		return res;

	}
	public static String GetCurrentPlaylistId() {
	String res="https://api.spotify.com/v1/me/playlists";
	return res;
}
}