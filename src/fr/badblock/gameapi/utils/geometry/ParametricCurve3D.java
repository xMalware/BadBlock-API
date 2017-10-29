package fr.badblock.gameapi.utils.geometry;

import java.util.ArrayList;
import java.util.List;

import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Représente une courbe paramétrée tridimensionnelle pour ętre utiliser pour
 * des particules, par <b>exemple</b>. La courbe peut ętre bidimensionnelle, il
 * suffit de définir une de ses coordonnées comme fixe.<br>
 * On rappel qu'une courbe paramétrée peut ętre :
 * <ul>
 * <li>De la famille des cercles (cercle, ellipse, spirale) et leurs équivalents
 * tridimensionnels</li>
 * <li>Une droite</li>
 * <li>Pleins de choses géniales que je ne pourrais pas décrire ... ;)</li>
 * </ul>
 * 
 * @author LeLanN
 */
public abstract class ParametricCurve3D {
	public final List<Vector3f> getPoints(double tMinValue, double tMaxValue, int pointsCount) {
		List<Vector3f> result = new ArrayList<>();
		double add = (tMaxValue - tMinValue) / pointsCount;

		for (double t = tMinValue; t <= tMaxValue; t += add) {
			try {
				result.add(new Vector3f(x(t), y(t), z(t)));
			} catch (Exception e) {
				/*
				 * Si t est une valeur interdite d'une des fonctions ... bobo ;)
				 */
			}
		}

		return result;
	}

	/**
	 * Renvoit la coordonnée x en fonction de t
	 * 
	 * @param t
	 *            La variable t c'est ŕ dire la 'position' dans la courbe
	 *            paramétrique
	 * @return La coordonnée
	 */
	public abstract double x(double t);

	/**
	 * Renvoit la coordonnée y en fonction de t
	 * 
	 * @param t
	 *            La variable t c'est ŕ dire la 'position' dans la courbe
	 *            paramétrique
	 * @return La coordonnée
	 */
	public abstract double y(double t);

	/**
	 * Renvoit la coordonnée z en fonction de t
	 * 
	 * @param t
	 *            La variable t c'est ŕ dire la 'position' dans la courbe
	 *            paramétrique
	 * @return La coordonnée
	 */
	public abstract double z(double t);
}
