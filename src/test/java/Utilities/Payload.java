package Utilities;

import java.util.HashMap;
import java.util.Map;

public class Payload {
	public static Map<String, Object> createPlaylist() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name","playlist_3");

		map.put("description","New playlist");
		map.put("public",false);
		return map;
	}

	public static Map<String, Object> updatePlaylist() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name","playlist2");

		map.put("description","updated playlist name");
		map.put("public",false);

		return map;
	}
}
