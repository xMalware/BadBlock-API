package fr.badblock.gameapi.particles;

import org.bukkit.util.Vector;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente un effet de particule.<br>
 * Récupérable avec {@link GameAPI#createParticleEffect(ParticleEffectType)}<br>
 * Envoyable avec {@link BadblockPlayer#sendParticle(org.bukkit.Location, ParticleEffect)}
 * @author LeLanN
 */
public interface ParticleEffect {
	/**
	 * Récupère la particule à afficher.
	 * @return La particule
	 */
	public ParticleEffectType getType();
	
	/**
	 * Définit la particule à afficher
	 * @param type La particule
	 * @return L'effet
	 */
	public ParticleEffect setType(ParticleEffectType type);
	
	/**
	 * Récupère les datas de l'entité. Voir {@link ParticleData} pour savoir quand les appliquer.
	 * @return Les datas
	 */
	public ParticleData getData();
	
	/**
	 * Définit les datas de l'entité. Voir {@link ParticleData} pour savoir quand les appliquer.
	 * @param data Les datas
	 * @return L'effet
	 */
	public ParticleEffect setData(ParticleData data);
	
	/**
	 * Vérifie si la particule s'affiche de loin
	 * @return Si elle s'affiche de loin
	 */
	public boolean isLongDistance();
	
	/**
	 * Définit si la particule s'affiche de loin
	 * @param longDistance Si elle s'affiche de loin
	 * @return L'effet
	 */
	public ParticleEffect setLongDistance(boolean longDistance);
	
	/**
	 * Récupère la vélocité de la particule
	 * @return La vélocité
	 */
	public Vector getOffset();
	
	/**
	 * Définit la vélocité de la particule
	 * @param offset La vélocité
	 * @return L'effet
	 */
	public ParticleEffect setOffset(Vector offset);
	
	/**
	 * Récupère la vitesse de la particule. Voir {@link ParticleEffectType} pour voir l'effet sur chaque particule.
	 * @return La vitesse
	 */
	public float getSpeed();
	
	/**
	 * Définit la vitesse de la particule. Voir {@link ParticleEffectType} pour voir l'effet sur chaque particule.
	 * @param speed La vitesse
	 * @return L'effet
	 */
	public ParticleEffect setSpeed(float speed);
	
	/**
	 * Récupère le nombre de particules à afficher
	 * @return Le nombre de particules
	 */
	public int getAmount();
	
	/**
	 * Définit le nombre de particules à afficher
	 * @param amount Le nombre de particules
	 * @return L'effet
	 */
	public ParticleEffect setAmount(int amount);
}
