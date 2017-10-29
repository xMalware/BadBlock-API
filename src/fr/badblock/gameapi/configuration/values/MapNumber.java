package fr.badblock.gameapi.configuration.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un nombre
 * 
 * @author LeLanN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapNumber implements MapValuePrimitive<Number> {
	private Number handle = 0;

	@Override
	public void from(JsonElement json) {
		handle = json.getAsNumber();
	}

	@Override
	public JsonElement to() {
		return new JsonPrimitive(handle);
	}
}