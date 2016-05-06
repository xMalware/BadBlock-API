package fr.badblock.gameapi.configuration.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class MapString implements MapValuePrimitive<String> {
	private String handle = "value";

	@Override
	public void from(JsonElement json) {
		handle = json.getAsString();
	}

	@Override
	public JsonElement to() {
		return new JsonPrimitive(handle);
	}
}