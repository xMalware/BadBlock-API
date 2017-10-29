package fr.badblock.gameapi.particles;

import org.bukkit.util.Vector;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente un effet de particule.<br>
 * Récupérable avec {@link GameAPI#createParticleEffect(ParticleEffectType)}<br>
 * Envoyable avec
 * {@link BadblockPlayer#sendParticle(org.bukkit.Location, ParticleEffect)}
 * 
 * @author LeLanN
 */
public interface ParticleEffect {
	/**
	 * Récupčre le nombre de particules ŕ afficher
	 * 
	 * @return Le nombre de particules
	 */
	public int getAmount();

	/**
	 * Récupčre les datas de l'entité. Voir {@link ParticleData} pour savoir
	 * quand les appliquer.
	 * 
	 * @return Les datas
	 */
	public ParticleData getData();

	/**
	 * Récupčre la vélocité de la particule
	 * 
	 * @return La vélocité
	 */
	public Vector getOffset();

	/**
	 * Récupčre la vitesse de la particule. Voir {@link ParticleEffectType} pour
	 * voir l'effet sur chaque particule.
	 * 
	 * @return La vitesse
	 */
	public float getSpeed();

	/**
	 * Récupčre la particule ŕ afficher.
	 * 
	 * @return La particule
	 */
	public ParticleEffectType getType();

	/**
	 * Vérifie si la particule s'affiche de loin
	 * 
	 * @return Si elle s'affiche de loin
	 */
	public boolean isLongDistance();

	/**
	 * Définit le nombre de particules ŕ afficher
	 * 
	 * @param amount
	 *            Le nombre de particules
	 * @return L'effet
	 */
	public ParticleEffect setAmount(int amount);

	/**
	 * Définit les datas de l'entité. Voir {@link ParticleData} pour savoir
	 * quand les appliquer.
	 * 
	 * @param data
	 *            Les datas
	 * @return L'effet
	 */
	public ParticleEffect setData(ParticleData data);

	/**
	 * Définit si la particule s'affiche de loin
	 * 
	 * @param longDistance
	 *            Si elle s'affiche de loin
	 * @return L'effet
	 */
	public ParticleEffect setLongDistance(boolean longDistance);

	/**
	 * Définit la vélocité de la particule
	 * 
	 * @param offset
	 *            La vélocité
	 * @return L'effet
	 */
	public ParticleEffect setOffset(Vector offset);

	/**
	 * Définit la vitesse de la particule. Voir {@link ParticleEffectType} pour
	 * voir l'effet sur chaque particule.
	 * 
	 * @param speed
	 *            La vitesse
	 * @return L'effet
	 */
	public ParticleEffect setSpeed(float speed);

	/**
	 * Définit la particule ŕ afficher
	 * 
	 * @param type
	 *            La particule
	 * @return L'effet
	 */
	public ParticleEffect setType(ParticleEffectType type);
}
