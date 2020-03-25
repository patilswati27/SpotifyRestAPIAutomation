package Utilities;

public class Payload {
	public static String createPlaylist() {
		String payload="{\r\n" + 
				"  \"name\": \"Playlist_1\",\n" + 
				"  \"description\": \"New playlist description\",\n" + 
				"  \"public\": false\n" + 
				"}";
		return payload;
			
}
}