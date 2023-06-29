package br.senac.rj.banco.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaAutorLivro;
/**
 * Essa classe é responsável pela apresentação dos elementos presentes no Menu de Dados Editoriais
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class SwingAutorLivro {
	/**
	 * Este método é responsável pela apresentação dos elementos presentes no menu de Dados Editoriais 	
	 */

	public static void apresentarMenu() {
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Cadastro de Dados Editoriais"); // Janela Normal
		janelaPrincipal.setTitle("Consulta de Dados Editoriais");
		janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "N�o");
		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// Define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Atualizar");
		menuBar.add(menuAtualizar);
		// Cria e adiciona um item simples para o menu
		JMenuItem menuAutorLivro = new JMenuItem("Dados Editoriais");
		menuAtualizar.add(menuAutorLivro);
		// Criar a janela de atualiza��o da conta
		JFrame janelaAutorLivro = JanelaAutorLivro.criarJanelaAutorLivro();
		// Adiciona a��o para o item do menu
		menuAutorLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaAutorLivro.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
