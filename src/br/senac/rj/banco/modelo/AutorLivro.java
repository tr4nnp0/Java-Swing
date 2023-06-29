package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
/**
 * Classe utilizada para operações relacionadas a gestão de dados editoriais(Consulta, Cadastro, Atualização, Exclusão)
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class AutorLivro {
	private int codautor;
	private int codlivro;
	private int codeditora;
	private String editora;


	public AutorLivro() {
		this.editora = "";
	
	}

	public AutorLivro(int numCodAutor, int numCodLivro, int numCodEditora) {
		this();
		this.codautor = numCodAutor;
		this.codlivro = numCodLivro;
		this.codeditora = numCodEditora;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getCodAutor() {
		return codautor;
	}

	public void setCodAutor(int numCodAutor) {
		this.codautor = numCodAutor;
	}

	public int getCodLivro() {
		return codlivro;
	}

	public void setCodLivro(int numCodLivro) {
		this.codlivro = numCodLivro;
	}
	
	public int getCodEditora() {
		return codeditora;
	}

	public void setCodEditora(int numCodEditora) {
		this.codeditora = numCodEditora;
	}
	/**
	 * Método utlizado para cadastro de dados editoriais a partir dos seguintes atributos
	 * @param numCodAutor - Código do autor
	 * @param numCodLivro - Código do Livro
	 * @param numCodEditora - Código da Editora
	 * @param editora - Nome da Editora
	 * @return Retornará um valor booleano com o status da operação
	 */
	public boolean cadastrarDados(int numCodAutor, int numCodLivro, int numCodEditora, String editora) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into autorlivro set codautor=?, codlivro=?, codeditora=?, editora=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numCodAutor); 
			ps.setInt(2, numCodLivro); 
			ps.setInt(3, numCodEditora);
			ps.setString(4, editora);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("N�o foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar os dados: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	/**
	 * Método utilizado para consulta de dados editorais a partir dos seguintes atributos:
	 * @param numCodAutor - Código do autor
	 * @param numCodLivro - Código do Livro
	 * @return Retornará 4 valores do banco de acordo com os atributos preenchidos.
	 */
	public boolean consultarDados(int numCodAutor, int numCodLivro) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from autorlivro where codautor=? and codlivro=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numCodAutor); 
			ps.setInt(2, numCodLivro); 
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Dados nao cadastrados!");
				return false; // Livro  n�o cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.codautor = rs.getInt("codautor");
					this.codlivro = rs.getInt("codlivro");
					this.codeditora = rs.getInt("codeditora");
					this.editora = rs.getString("editora");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar os dados: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Método utilizado para atualização dos dados editoriais para a partir dos seguintes atributos:
	 * @param numCodAutor - Código do autor
	 * @param numCodLivro - Código do livro
	 * @param numCodEditora - Código da Editora
	 * @param editora - nome da Editóra
	 * @return Retorna o status dos dados atualizados
	 */

	public boolean atualizarDados(int numCodAutor, int numCodLivro, int numCodEditora, String editora) {
		if (!consultarDados(numCodAutor, numCodLivro))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update autorlivro set editora=? and codeditora=? where codautor=? and codlivro=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numCodAutor); 
				ps.setInt(2, numCodLivro); 				
				ps.setInt(3, numCodEditora); 
				ps.setString(4, editora); 
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi feita a atualiza��o!");
				else
					System.out.println(totalRegistrosAfetados);
				return true;
				} catch (SQLException erro) {
				if (erro.getMessage().contains("foreign key constraint")) {
	                System.out.println("Erro ao cadastrar os dados: Não é possível cadastrar o livro devido a restrições de chave estrangeira!");
	            } else {
				System.out.println("Erro ao cadastrar os dados: " + erro.toString());
	            }
				return false;
				} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Método utlizado para a exclusão de dados editoriais de acordo com os seguintes atributos
	 * @param numCodAutor - Código do autor
	 * @param numCodLivro - Código do Livro
	 * @param numCodEditora - Código da Editora
	 * @param editora - Nome da Editora
	 * @return Retornará um valor booleano com o status da exclusão
	 */
	public boolean deletarDados(int numCodAutor, int numCodLivro, int numCodEditora, String editora) {
		if (!consultarDados(numCodAutor, numCodLivro))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "delete from autorlivro where codautor=? and codlivro=? and codeditora=? and editora=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numCodAutor); 
				ps.setInt(2, numCodLivro); 				
				ps.setInt(3, numCodEditora); 
				ps.setString(4, editora);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi realizada a exclusão!");
				else
					System.out.println("Dados excluídos com sucesso!");
				return true;
			} catch (SQLException erro) {
				if (erro.getMessage().contains("foreign key constraint")) {
	                System.out.println("Erro ao deletar dados: Não é possível excluir o livro devido a restrições de chave estrangeira!");
	            } else {
				System.out.println("Erro ao deletar dados do livro: " + erro.toString());
	            }
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}
