package fr.badblock.gameapi.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.badblock.gameapi.utils.general.Callback;

/**
 * Représente la base de donnée SQL
 * 
 * @author LeLanN/xMalware
 */
public interface SQLDatabase {

	/**
	 * Récupère un statement pour une gestion plus personnalisée du SQL. Ne pas
	 * oublier de le close :0
	 * 
	 * @return Le statement
	 */
	public Statement createStatement() throws Exception;

	/**
	 * Récupère un statement permettant de faire des requêtes préparées. Ne pas
	 * oublier de le close :0
	 * 
	 * @return Le statement
	 */
	public PreparedStatement preparedStatement(String request) throws Exception;

	/**
	 * Appeler un gestionnaire Multithreading qui va gérer en asynchrone la
	 * requête La requête peut être donc traitée de sorte qu'elle ne renvoit
	 * aucune information ou qu'elle renvoit des données, se référer à
	 * {@link fr.badblock.gameapi.databases.SQLRequestType}
	 * 
	 * @param request
	 *            > Requête
	 * @param sqlRequestType
	 *            > Type de la requête, DATA OR QUERY
	 * @param callback
	 *            > Réponse de la requête
	 */
	public void call(String request, SQLRequestType sqlRequestType, Callback<ResultSet> callback);

	/**
	 * Appeler un gestionnaire Multithreading qui va gérer en asynchrone la
	 * requête La requête peut être donc traitée de sorte qu'elle ne renvoit
	 * aucune information ou qu'elle renvoit des données, se référer à
	 * {@link fr.badblock.gameapi.databases.SQLRequestType}
	 * 
	 * @param request
	 *            > Requête
	 * @param sqlRequestType
	 *            > Type de la requête, DATA OR QUERY
	 */
	public void call(String request, SQLRequestType sqlRequestType);

}
