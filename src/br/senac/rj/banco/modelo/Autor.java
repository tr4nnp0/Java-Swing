package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe utilizada para operações relacionadas a gestão de autores(Consulta, Cadastro, Atualização, Exclusão)
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class Autor {
	private int matricula;
	private int cpf;
	private String nacionalidade;
	private String nome;
	//protected double saldo;
	//public static int totalContas;
	

	public Autor() {
		this.nome = "";
		//Conta.totalContas++;
	}

	public Autor(int numMatricula, int numCPF, String nacionalidade) {
		this();
		this.matricula = numMatricula;
		this.cpf = numCPF;
		this.nacionalidade = nacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getCPF() {
		return cpf;
	}

	public void setCPF(int cpf) {
		this.cpf = cpf;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setString(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	/**
	 * Método utlizado para o cadastro de autores a partir dos seguintes atributos
	 * @param numMatricula - Número de Matrícula do autor
	 * @param nome - Nome do Autor
	 * @param nacionalidade - Nacionalidade do Autor
	 * @param numCPF - CPF do Autor
	 * @return Retornará um valor booleano com o status do cadastramento
	 */

	public boolean cadastrarAutor(int numMatricula, String nome, String nacionalidade, int numCPF) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into autor set matricula=?, nome=?, nacionalidade=?, CPF=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numMatricula); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
			ps.setString(3, nacionalidade); // Substitui o terceiro par�metro da consulta pela conta informada
			ps.setInt(4, numCPF); // Substitui o quarto par�metro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("N�o foi feito o cadastro!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o autor: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Método utlizado para consulta de autores de acordo com atributos abaixo
	 * @param numMatricula - Número de Matrícula do autor
	 * @param nome - Nome do Autor
	 * @return Retornará 4 valores em relacionados com os atributos preenchidos
	 */

	public boolean consultarAutor(int numMatricula, String nome) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from autor where matricula=? and nome=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, numMatricula); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Autor nao cadastrado!");
				return false; // Livro  n�o cadastrado
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.matricula = rs.getInt("matricula");
					this.nome = rs.getString("nome");
					this.nacionalidade = rs.getString("nacionalidade");
					this.cpf = rs.getInt("CPF");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o autor do livro: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Método utilizado para atualização de dados de autores a partir dos seguintes atributos:
	 * @param numMatricula - Número de matrícula do autor
	 * @param nome - Nome do autor
	 * @param nacionalidade - Nacionalidade do autor
	 * @param numCPF - CPF do autor
	 * @return retonará um valor booleano com o status da atualização
	 */
	public boolean atualizarDadosAutor(int numMatricula, String nome, String nacionalidade, int numCPF) {
		if (!consultarAutor(numMatricula, nome))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update autor set CPF=?, nacionalidade=? where matricula=? and nome=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numMatricula); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
				ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
				ps.setString(3, nacionalidade); // Substitui o terceiro par�metro da consulta pela conta informada
				ps.setInt(4, numCPF); // Substitui o quarto par�metro da consulta pelo titular informado
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi feita a atualiza��o!");
				else
					System.out.println("Atualiza��o realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Autor: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
		/**
		 * Método utilizado para exclusão de autores a partir dos seguintes atributos
		 * @param numMatricula - Número de matrícula do autor
		 * @param nome - Nome do Autor
		 * @param nacionalidade - Nacionalidade do autor
		 * @param numCPF - CPF do autor
		 * @return retornará um valor booleano com o status da exclusão
		 */
	public boolean deletarDadosAutor(int numMatricula, String nome, String nacionalidade, int numCPF) {
		if (!consultarAutor(numMatricula, nome))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				//String sql = "update conta set titular=?, saldo=? where agencia=? and numero=?";
				String sql = "delete from livro where matricula=? and nome=? and nacionalidade=? and CPF=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setInt(1, numMatricula); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
				ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
				ps.setString(3, nacionalidade); // Substitui o terceiro par�metro da consulta pela conta informada
				ps.setInt(4, numCPF); // Substitui o quarto par�metro da consulta pelo titular informado
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi realizada a exclusão!");
				else
					System.out.println("Dados excluídos com sucesso!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao deletar dados do produto: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}
