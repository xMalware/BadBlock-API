package fr.badblock.gameapi.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Classe permettant d'utiliser facilement la réfléction à l'échelle d'une unique classe (principalement la gestion des Fields°.
 * @author LeLanN
 */
@Data
public class Reflector {
	private final Object     reflected;
	private final Class<?>[] clazz;

	/**
	 * Crée un nouveau Reflector à partir d'un objet.
	 * @param object L'objet sur lequel les modifications / lectures aurront lieues.
	 */
	public Reflector(Object object){
		this(object, object.getClass());
	}

	public Reflector(Object object, Class<?> clazz){
		this.reflected = object;

		List<Class<?>> allClass = new ArrayList<>();
		allClass.add(clazz);

		addSuper(clazz, allClass);

		this.clazz = allClass.toArray(new Class<?>[0]);
	}

	private void addSuper(Class<?> clazz, List<Class<?>> allClass){
		if(!clazz.getSuperclass().equals(Object.class)){
			allClass.add(clazz.getSuperclass());
			addSuper(clazz.getSuperclass(), allClass);
		}
	}

	public Object getFieldValue(String name) throws Exception {
		Field field = getDeclaredField(name);

		if(!field.isAccessible())
			field.setAccessible(true);

		return field.get(reflected);
	}

	public Object getStaticFieldValue(String name) throws Exception {
		Field field = getDeclaredField(name);

		if(!field.isAccessible())
			field.setAccessible(true);

		return field.get(null);
	}

	public void setFieldValue(String name, Object object) throws Exception {
		Field field = getDeclaredField(name);

		if(!field.isAccessible())
			field.setAccessible(true);

		ReflectionUtils.removeFinal(field);
		field.set(reflected, object);
	}

	public void setStaticFieldValue(String name, Object object) throws Exception {
		Field field = getDeclaredField(name);
		new Reflector(field).setFieldValue("modifiers", Modifier.STATIC | Modifier.PUBLIC);

		field.set(null, object);
	}

	protected Field getDeclaredField(String name) throws Exception {
		Field field = null;

		for(Class<?> clazz : this.clazz){
			try {
				field = clazz.getDeclaredField(name);
				return field; // le field semble trouvé ! :)
			} catch (NoSuchFieldException e) {

			} catch(SecurityException e){
				throw new SecurityException(e);
			}
		}

		throw new NoSuchFieldException(name);
	}
}
