package com.elomega;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoJdbc {

	// pour lire a partir du console
	private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {

		Properties props = new Properties();
		try (FileInputStream file = new FileInputStream("model/conf.properties")) { // on charge le fichier
																					// conf.properties
			props.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Class.forName(props.getProperty("jdbc.driver.class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // on charge le pilote mysql-connector

		// on charge l'url , le login et le passworld
		String url = props.getProperty("jdbc.url");
		String dbLogin = props.getProperty("jdbc.login");
		String dbPassworld = props.getProperty("jdbc.passworld");

		try (Connection connection = DriverManager.getConnection(url, dbLogin, dbPassworld)) { // on etablie la
																								// connexion qui sera
																								// automatiquement
																								// fermer a la sortie du
																								// bloc try
			String nomUtilisateur = "";
			System.out.println("Bienvenue! dans prise en main de JDBC");
			while (true) { // ici c'est une boucle infini , tant que la connexion n'a pas reussie , on
							// continue
				System.out.print("Login:");
				String login = "";
				try {
					login = keyboard.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.print("passworld:");
				String passworld = "";
				try {
					passworld = keyboard.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String strSql = "SELECT * FROM utilisateur WHERE login='" + login + "'AND passworld='" + passworld
						+ "'"; // pour verifier si l'utilisateur est dans la base
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(strSql)) { // on execute la requete
					if (resultSet.next()) {
						strSql = "UPDATE utilisateur SET nombreDeConnection=nombreDeConnection+1 WHERE idUser="
								+ resultSet.getInt("idUser"); // si c'est le cas on incremente le nombre de connexion
						try (Statement stUpdate = connection.createStatement()) {
							stUpdate.executeUpdate(strSql);
						}
						nomUtilisateur = resultSet.getString(2);
						break;
					}
					System.out.println("connexion echouee");
				}
			}
			System.out.println(nomUtilisateur + " vous etez connecter!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
