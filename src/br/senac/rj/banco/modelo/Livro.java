package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe utilizada para operações relacionadas a gestão de livros(Consulta, Cadastro, Atualização, Exclusão)
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class Livro {
	private int codigo;
	private int preco;
	private int anoLancamento;
	private String titulo;


	public Livro() {
		this.titulo = "";

	}

	public Livro(int numCodigo, int valorPreco, int AnoLancamento) {
		this();
		this.codigo = numCodigo;
		this.preco = valorPreco;
		this.anoLancamento = AnoLancamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	/**
	 * Método utlizado para cadastro de novos exemplares a partir dos seguintes atributos
	 * @param numCodigo - Número de Código do Livro
	 * @param titulo - Título do Livro
	 * @param anoLancamento - Ano do Lançamento do Livro
	 * @param valorPreco - Preço do Livro
	 * @return Será retornado um valor booleano com o status de do cadastro
	 */

	public boolean cadastrarLivro(int numCodigo, String titulo, int anoLancamento, int valorPreco) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into livro set codigo=?, titulo=?, anoLancamento=?, preco=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numCodigo); // Substitui o primeiro par�metro da consulta pelo codigo informado
			ps.setString(2, titulo); // Substitui o segundo par�metro da consulta pelo título informado
			ps.setInt(3, anoLancamento); // Substitui o terceiro par�metro da consulta pelo ano de lançamento informado
			ps.setInt(4, valorPreco); // Substitui o quarto par�metro da consulta pelo preco informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("N�o foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o livro: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Método utlizado para consulta de livros a partir dos seguintes atributos:
	 * @param numCodigo - Número de Código do Livro
	 * @param titulo - Titulo do Livro
	 * @return Retornará 4 atributos que correspondem com  pesquisa
	 */
	public boolean consultarLivro(int numCodigo, String titulo) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from livro where codigo=? and titulo=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numCodigo); // Substitui o primeiro par�metro da consulta pelo codigo informado
			ps.setString(2, titulo); // Substitui o segundo par�metro da consulta pelo titulo informado
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Livro nao cadastrado!");
				return false; // Livro  n�o cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.codigo = rs.getInt("codigo");
					this.titulo = rs.getString("titulo");
					this.anoLancamento = rs.getInt("anoLancamento");
					this.preco = rs.getInt("preco");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o livro: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Método utilizado para atualização de dados dos Livros a partir dos seguintes atributos
	 * @param numCodigo - Código do Livro 
	 * @param titulo - Titulo do Livro
	 * @param anoLancamento - no de Lançamento do Livro
	 * @param valorPreco - Preço do Livro
	 * @return Um valor booleano com o status da atualização no banco
	 */
	public boolean atualizarDadosLivro(int numCodigo, String titulo, int anoLancamento, int valorPreco) {
		if (!consultarLivro(numCodigo, titulo))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update livro set preco=?, anoLancamento=? where codigo=? and titulo=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numCodigo); // Substitui o primeiro par�metro da consulta pelo codigo informado
				ps.setString(2, titulo); // Substitui o segundo par�metro da consulta pelo titulo informado
				ps.setInt(3, anoLancamento); // Substitui o terceiro par�metro da consulta pelo ano de lançamento informado
				ps.setInt(4, valorPreco); // Substitui o quarto par�metro da consulta pelo preco informado
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi feita a atualiza��o!");
				else
					System.out.println("Atualiza��o realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Livro: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Método utilizado para deletar livros no banco a partir dos seguintes atributos
	 * @param numCodigo - Código do Livro
	 * @param titulo - Titulo do Livro
	 * @param anoLancamento - Ano de Lançamento
	 * @param valorPreco - Preço do Livro
	 * @return Valor booleano com o status da exclusão 
	 */
	public boolean deletarDadosLivro(int numCodigo, String titulo, int anoLancamento, int valorPreco) {
		if (!consultarLivro(numCodigo, titulo))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				//String sql = "update conta set titular=?, saldo=? where agencia=? and numero=?";
				String sql = "delete from livro where codigo=? and titulo=? and anoLancamento=? and preco=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numCodigo); // Substitui o primeiro par�metro da consulta pelo codigo informado
				ps.setString(2, titulo); // Substitui o segundo par�metro da consulta pelo titulo informado
				ps.setInt(3, anoLancamento); // Substitui o terceiro par�metro da consulta pelo ano de lançamento informado
				ps.setInt(4, valorPreco); // Substitui o quarto par�metro da consulta pelo preco informado
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi realizada a exclusão!");
				else
					System.out.println("Dados excluídos com sucesso!");
				return true;
			} catch (SQLException erro) {
				if (erro.getMessage().contains("foreign key constraint")) {
	                System.out.println("Erro ao deletar dados do livro: Não é possível excluir o livro devido a restrições de chave estrangeira!");
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
