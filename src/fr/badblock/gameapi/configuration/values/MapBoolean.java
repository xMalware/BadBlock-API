package fr.badblock.gameapi.configuration.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapBoolean implements MapValuePrimitive<Boolean> {
	private Boolean handle = false;

	@Override
	public void from(JsonElement json) {
		handle = json.getAsBoolean();
	}

	@Override
	public JsonElement to() {
		return new JsonPrimitive(handle);
	}
}
