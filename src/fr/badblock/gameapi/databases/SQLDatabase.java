package fr.badblock.gameapi.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.badblock.gameapi.utils.general.Callback;

/**
 * Représente la base de donnée SQL
 * @author LeLanN
 */
public interface SQLDatabase {
	/**
	 * Récupère un statement pour une gestion plus personnalisée du SQL. Ne pas oublier de le close :0
	 * @return Le statement
	 */
	public Statement createStatement() throws Exception;
	
	/**
	 * Récupère un statement permettant de faire des requêtes préparées. Ne pas oublier de le close :0
	 * @return Le statement
	 */
	public PreparedStatement preparedStatement(String request) throws Exception;
	
	/**
	 * Envoit une requête de query
	 * @param request La requête
	 * @return La réponse
	 */
	public ResultSet query(String request) throws Exception;
	
	/**
	 * Envoit une requête de query
	 * @param request La requête
	 * @param callback Le callback pour la réponse
	 */
	public void queryAsynchronously(String request, Callback<ResultSet> callback);
	
	/**
	 * Envoit une requête d'update
	 * @param request La requ$ete
	 * @param synchronously Si la requête doit être fait de manière synchrone
	 */
	public void update(String request, boolean synchronously) throws Exception;
}
