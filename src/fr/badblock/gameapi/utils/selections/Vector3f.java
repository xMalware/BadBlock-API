package fr.badblock.gameapi.utils.selections;

import org.bukkit.Location;
import org.bukkit.World;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Représente un vecteur à trois dimensions
 * 
 * @author LeLanN
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vector3f {
	@Getter
	private double x, y, z;

	public Vector3f() {
		this(0, 0, 0);
	}

	public Vector3f(Vector3f v) {
		this(v.x, v.y, v.z);
	}

	public Vector3f(Location v) {
		this(v.getX(), v.getY(), v.getZ());
	}

	public Vector3f add(Vector3f vec) {
		x += vec.getX();
		y += vec.getY();
		z += vec.getZ();

		return this;
	}

	public Vector3f sub(Vector3f vec) {
		x -= vec.getX();
		y -= vec.getY();
		z -= vec.getZ();

		return this;
	}

	public Vector3f mul(Vector3f vec) {
		x *= vec.getX();
		y *= vec.getY();
		z *= vec.getZ();

		return this;
	}

	public Vector3f div(Vector3f vec) {
		x /= vec.getX();
		y /= vec.getY();
		z /= vec.getZ();

		return this;
	}

	public Vector3f add(double v) {
		x += v;
		y += v;
		z += v;

		return this;
	}

	public Vector3f sub(double v) {
		x -= v;
		y -= v;
		z -= v;

		return this;
	}

	public Vector3f mul(double v) {
		x *= v;
		y *= v;
		z *= v;

		return this;
	}

	public Vector3f div(double v) {
		x /= v;
		y /= v;
		z /= v;

		return this;
	}

	// ---- X

	public Vector3f setX(double x) {
		this.x = x;
		return this;
	}

	public Vector3f addX(double v) {
		x += v;
		return this;
	}

	public Vector3f subX(double v) {
		x -= v;
		return this;
	}

	// ----- Y
	public Vector3f setY(double y) {
		this.y = y;
		return this;
	}

	public Vector3f addY(double v) {
		y += v;
		return this;
	}

	public Vector3f subY(double v) {
		y -= v;
		return this;
	}

	// ----- Z
	public Vector3f setZ(double z) {
		this.z = z;
		return this;
	}

	public Vector3f addZ(double v) {
		z += v;
		return this;
	}

	public Vector3f subZ(double v) {
		z -= v;
		return this;
	}

	public Vector3f add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;

		return this;
	}

	/**
	 * Calcul la longueur de se vecteur
	 * 
	 * @return La longueur
	 */
	public double length() {
		return (double) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Normalise le vecteur
	 * 
	 * @return L'instance actuelle du vecteur
	 */
	public Vector3f normalize() {
		x /= length();
		y /= length();
		z /= length();

		return this;
	}

	/**
	 * Cherche la distance entre deux vecteurs
	 * 
	 * @param v
	 *            Le deuxième vecteur
	 * @return La distance
	 */
	public double distance(Vector3f v) {
		return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(z - v.z, 2) + Math.pow(z - v.z, 2));
	}

	/**
	 * Convertir le Vecteur en une Location Bukkit
	 * 
	 * @param w
	 *            Le monde de la Location
	 * @return La nouvelle Location
	 */
	public Location bukkit(World w) {
		return new Location(w, x, y, z);
	}

	/**
	 * Crée un nouveau vecteur ayant les même coordonnées
	 * 
	 * @return Le nouveau vecteur
	 */
	public Vector3f clone() {
		return new Vector3f(this);
	}
}
