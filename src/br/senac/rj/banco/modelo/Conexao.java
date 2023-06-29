package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
/**
 * Essa classe é responsável pela conexão com o banco de dados 
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class Conexao {
	/**
	 * Esse método é utilizado para a conexão com o banco de dados através da URl, usúario e senha
	 * @return O status da conexão
	 */
	public static Connection conectaBanco() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/bancoprojeto"; // URL do banco de dados
			String user = "root"; // nome do usu�rio do banco
			String password = ""; // senha do banco
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�o ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�o identificado: " + erro.toString());
		} 
		return conexao;
	}
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conex�o: " + erro.toString());
		}
	}
}