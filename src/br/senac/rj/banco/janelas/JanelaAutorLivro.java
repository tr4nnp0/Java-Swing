package br.senac.rj.banco.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.AutorLivro;
/**
 * Essa classe descreve as informações e medidas para a Janela e Menu de Dados Editoriais
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */

public class JanelaAutorLivro {
	/**
	 *	 Esse método define os parametros das Janelas para cadastro de dados editoriais
	 */
	public static JFrame criarJanelaAutorLivro() {
		// Define a janela
		JFrame janelaAutorLivro = new JFrame("Atualiza��o de dados editoriais"); // Janela Normal
		janelaAutorLivro.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaAutorLivro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAutorLivro.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaAutorLivro.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCodAutor = new JLabel("Codautor ");
		JLabel labelCodLivro = new JLabel("CodLivro: ");
		JLabel labelCodEditora = new JLabel("CodEditora: ");
		JLabel labelEditora = new JLabel("Editora: ");
		// Posiciona os labels na janela
		labelCodAutor.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelCodLivro.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelCodEditora.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelEditora.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextCodAutor = new JTextField();
		JTextField jTextCodLivro = new JTextField();
		JTextField jTextCodEditora = new JTextField();
		JTextField jTextEditora = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCodAutor.setEnabled(true);
		jTextCodLivro.setEnabled(true);
		jTextCodEditora.setEnabled(false);
		jTextEditora.setEnabled(false);
		// Posiciona os input box
		jTextCodAutor.setBounds(180, 40, 50, 20);
		jTextEditora.setBounds(180, 120, 150, 20);
		jTextCodLivro.setBounds(180, 80, 50, 20);
		jTextCodEditora.setBounds(180, 160, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaAutorLivro.add(labelCodAutor);
		janelaAutorLivro.add(labelCodLivro);
		janelaAutorLivro.add(labelCodEditora);
		janelaAutorLivro.add(labelEditora);
		janelaAutorLivro.add(jTextCodAutor);
		janelaAutorLivro.add(jTextCodLivro);
		janelaAutorLivro.add(jTextCodEditora);
		janelaAutorLivro.add(jTextEditora);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janelaAutorLivro.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(30, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaAutorLivro.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(275, 200, 100, 20);
		janelaAutorLivro.add(botaoLimpar);
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(155, 200, 100, 20);
		janelaAutorLivro.add(botaoDeletar);
		botaoDeletar.setEnabled(false);
		// Define objeto conta para pesquisar no banco de dados
		AutorLivro autorlivro = new AutorLivro();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codautor = Integer.parseInt(jTextCodAutor.getText());
					int codlivro = Integer.parseInt(jTextCodLivro.getText());
					if (jTextCodAutor.getText().isEmpty() || jTextCodLivro.getText().isEmpty()) {
			                JOptionPane.showMessageDialog(janelaAutorLivro, "Preencha os campos codautor e codlivro corretamente!!");
			                return;
			        } //O programa não estava pegando esse erro de campo vazio na String
					botaoGravar.setEnabled(true);
					String editora;
					int codeditora;
					if (!autorlivro.consultarDados(codautor, codlivro))
						editora = "";
					else 
						editora = autorlivro.getEditora();
						jTextEditora.setText(editora);
						jTextCodAutor.setEnabled(false);
						jTextCodLivro.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextEditora.setEnabled(true);
						jTextEditora.requestFocus();
					if (!autorlivro.consultarDados(codautor, codlivro)) 
						codeditora = 0;
					else 
						codeditora = autorlivro.getCodEditora();
						jTextCodEditora.setText(String.valueOf(codeditora));
						jTextCodAutor.setEnabled(false);
						jTextCodLivro.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextCodEditora.setEnabled(true);
						jTextCodEditora.requestFocus();
				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(janelaAutorLivro,
							"Preencha os campos matricula e nome do autor corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaAutorLivro, "Deseja atualizar?", "Confirma��o",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
						int codautor;
						int codlivro;
						int codeditora;
						String editora;
			            try {
			            	codautor = Integer.parseInt(jTextCodAutor.getText());
			            	codlivro = Integer.parseInt(jTextCodLivro.getText());
			            	editora = jTextEditora.getText().trim();
			                String codeditoraText = jTextCodEditora.getText().trim();			     
			                if (codeditoraText.isEmpty() || editora.length() == 0) {
			                    JOptionPane.showMessageDialog(janelaAutorLivro, "Preencha todos os campos com valores válidos");
			                    return;
			                }
			                codeditora = Integer.parseInt(codeditoraText);
			               	} catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(janelaAutorLivro, "Esse tipo de variável não é aceito para esse campo");
			               return;
			            }			           
						if (!autorlivro.consultarDados(codautor, codlivro)) {
							if (!autorlivro.cadastrarDados(codautor, codlivro, codeditora, editora))
								JOptionPane.showMessageDialog(janelaAutorLivro, "Erro na inclus�o dos dados!");
							else
								JOptionPane.showMessageDialog(janelaAutorLivro, "Inclus�o realizada!");
						} else {
							if (!autorlivro.atualizarDados(codautor, codlivro, codeditora, editora))
								JOptionPane.showMessageDialog(janelaAutorLivro, "Erro na atualiza��o dos dados!");
							else
								JOptionPane.showMessageDialog(janelaAutorLivro, "Atualização realizada!");
						}

				}
		}
	});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCodAutor.setText(""); // Limpar campo
				jTextCodLivro.setText(""); // Limpar campo
				jTextCodEditora.setText(""); // Limpar campo
				jTextEditora.setText(""); // Limpar campo
				jTextCodAutor.setEnabled(true);
				jTextCodLivro.setEnabled(true);
				jTextCodEditora.setEnabled(false);
				jTextEditora.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextCodAutor.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaAutorLivro, "Deseja deletar?", "Confirmação", JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            int codautor = Integer.parseInt(jTextCodAutor.getText());
		            int codlivro = Integer.parseInt(jTextCodLivro.getText());
					int codeditora = Integer.parseInt(jTextCodEditora.getText());
					String editora = jTextEditora.getText();		            
		            if (autorlivro.deletarDados(codautor, codlivro, codeditora, editora)) {
		                JOptionPane.showMessageDialog(janelaAutorLivro, "Dados deletados com sucesso!");
		                jTextCodAutor.setText("");
		                jTextCodEditora.setText("");
		                jTextEditora.setText("");
		                jTextCodLivro.setText("");
		                jTextCodAutor.setEnabled(true);
		                jTextCodLivro.setEnabled(true);
		                jTextCodEditora.setEnabled(false);
		                jTextEditora.setEnabled(false);
		                botaoConsultar.setEnabled(true);
		                botaoGravar.setEnabled(false);
		                botaoDeletar.setEnabled(false);
		                jTextCodAutor.requestFocus();
		            } else {
		                JOptionPane.showMessageDialog(janelaAutorLivro, "Erro ao deletar a entrada!");
		            }
		        }
		    }
		});
		return janelaAutorLivro;
	}
}
