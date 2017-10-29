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
	 * Appeler un gestionnaire Multithreading qui va gérer en asynchrone la
	 * requęte La requęte peut ętre donc traitée de sorte qu'elle ne renvoit
	 * aucune information ou qu'elle renvoit des données, se référer ŕ
	 * {@link fr.badblock.gameapi.databases.SQLRequestType}
	 * 
	 * @param request
	 *            > Requęte
	 * @param sqlRequestType
	 *            > Type de la requęte, DATA OR QUERY
	 */
	public void call(String request, SQLRequestType sqlRequestType);

	/**
	 * Appeler un gestionnaire Multithreading qui va gérer en asynchrone la
	 * requęte La requęte peut ętre donc traitée de sorte qu'elle ne renvoit
	 * aucune information ou qu'elle renvoit des données, se référer ŕ
	 * {@link fr.badblock.gameapi.databases.SQLRequestType}
	 * 
	 * @param request
	 *            > Requęte
	 * @param sqlRequestType
	 *            > Type de la requęte, DATA OR QUERY
	 * @param callback
	 *            > Réponse de la requęte
	 */
	public void call(String request, SQLRequestType sqlRequestType, Callback<ResultSet> callback);

	/**
	 * Récupčre un statement pour une gestion plus personnalisée du SQL. Ne pas
	 * oublier de le close :0
	 * 
	 * @return Le statement
	 */
	public Statement createStatement() throws Exception;

	/**
	 * Récupčre un statement permettant de faire des requętes préparées. Ne pas
	 * oublier de le close :0
	 * 
	 * @return Le statement
	 */
	public PreparedStatement preparedStatement(String request) throws Exception;

}
