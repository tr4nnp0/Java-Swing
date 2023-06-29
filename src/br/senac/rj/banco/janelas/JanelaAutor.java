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

import br.senac.rj.banco.modelo.Autor;

/**
 *	 Essa classe descreve informações e medidas para  a Janela e menu de Autores
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */

public class JanelaAutor {
	/**
	 *	 Esse método define os parametros das Janelas para cadastro de autores.
	 */
	public static JFrame criarJanelaAutor() {
		// Define a janela
		JFrame janelaAutor = new JFrame("Atualiza��o de autor"); // Janela Normal
		janelaAutor.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaAutor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAutor.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaAutor.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelMatricula = new JLabel("Matricula: ");
		JLabel labelNome = new JLabel("Nome do Autor: ");
		JLabel labelNacionalidade = new JLabel("Nacionalidade: ");
		JLabel labelCPF = new JLabel("CPF do autor: ");
		// Posiciona os labels na janela
		labelMatricula.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelNacionalidade.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelCPF.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextMatricula = new JTextField();
		JTextField jTextCPF = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextNacionalidade = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextMatricula.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextCPF.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		// Posiciona os input box
		jTextMatricula.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 50, 20);
		jTextCPF.setBounds(180, 120, 150, 20);
		jTextNacionalidade.setBounds(180, 160, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaAutor.add(labelMatricula);
		janelaAutor.add(labelNome);
		janelaAutor.add(labelNacionalidade);
		janelaAutor.add(labelCPF);
		janelaAutor.add(jTextMatricula);
		janelaAutor.add(jTextNome);
		janelaAutor.add(jTextNacionalidade);
		janelaAutor.add(jTextCPF);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janelaAutor.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(30, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaAutor.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(275, 200, 100, 20);
		janelaAutor.add(botaoLimpar);
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(155, 200, 100, 20);
		janelaAutor.add(botaoDeletar);
		botaoDeletar.setEnabled(false);
		// Define objeto conta para pesquisar no banco de dados
		Autor autor = new Autor();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int matricula = Integer.parseInt(jTextMatricula.getText());
					String nome = jTextNome.getText();
					if (jTextMatricula.getText().isEmpty() || jTextNome.getText().isEmpty()) {
			                JOptionPane.showMessageDialog(janelaAutor, "Preencha os campos matricula e nome do autor corretamente!!");
			                return;
			        } //O programa não estava pegando esse erro de campo vazio na String
					botaoGravar.setEnabled(true);
					String nacionalidade;
					int cpf;
					if (!autor.consultarAutor(matricula, nome))
						nacionalidade = "";
					else 
						nacionalidade = autor.getNacionalidade();
						jTextNacionalidade.setText(nacionalidade);
						jTextMatricula.setEnabled(false);
						jTextNome.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextNacionalidade.setEnabled(true);
						jTextNacionalidade.requestFocus();
					if (!autor.consultarAutor(matricula, nome)) 
						cpf = 0;
					else 
						cpf = autor.getCPF();
						jTextCPF.setText(String.valueOf(cpf));
						jTextMatricula.setEnabled(false);
						jTextNome.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextCPF.setEnabled(true);
						jTextCPF.requestFocus();
				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(janelaAutor,
							"Preencha os campos matricula e nome do autor corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaAutor, "Deseja atualizar?", "Confirma��o",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
						int matricula = Integer.parseInt(jTextMatricula.getText());
			            String nome = jTextNome.getText().trim();
			            String nacionalidade = jTextNacionalidade.getText().trim();
			            int cpf;
			            try {
			                matricula = Integer.parseInt(jTextMatricula.getText());
			                nome = jTextNome.getText().trim();
			                nacionalidade = jTextNacionalidade.getText().trim();
			                String cpfText = jTextCPF.getText().trim();			     
			                if (cpfText.isEmpty()) {
			                    JOptionPane.showMessageDialog(janelaAutor, "Preencha todos os campos com valores válidos");
			                    return;
			                }
			                cpf = Integer.parseInt(cpfText);
			            	} catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(janelaAutor, "Preencha todos os campos com valores válidos");
			               return;
			            }			           
			            if (nacionalidade.length() == 0) {
			            	JOptionPane.showMessageDialog(janelaAutor, "Preencha o campo nacionalidade");
			            	jTextNacionalidade.requestFocus();
			            } else {
						if (!autor.consultarAutor(matricula, nome)) {
							if (!autor.cadastrarAutor(matricula, nome, nacionalidade, cpf))
								JOptionPane.showMessageDialog(janelaAutor, "Erro na inclus�o do autor!");
							else
								JOptionPane.showMessageDialog(janelaAutor, "Inclus�o realizada!");
						} else {
							if (!autor.atualizarDadosAutor(matricula, nome, nacionalidade, cpf))
								JOptionPane.showMessageDialog(janelaAutor, "Erro na atualiza��o do autor!");
							else
								JOptionPane.showMessageDialog(janelaAutor, "Atualização realizada!");
						}

				}
			}
		}
	});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextMatricula.setText(""); // Limpar campo
				jTextCPF.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextNacionalidade.setText(""); // Limpar campo
				jTextMatricula.setEnabled(true);
				jTextCPF.setEnabled(false);
				jTextNome.setEnabled(true);
				jTextNacionalidade.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextMatricula.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaAutor, "Deseja deletar?", "Confirmação", JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            int matricula = Integer.parseInt(jTextMatricula.getText());
		            String nome = jTextNome.getText().trim();
					String nacionalidade = jTextNacionalidade.getText();
					int cpf = Integer.parseInt(jTextCPF.getText());		            
		            if (autor.deletarDadosAutor(matricula, nome, nacionalidade, cpf)) {
		                JOptionPane.showMessageDialog(janelaAutor, "Dados deletados com sucesso!");
		                jTextMatricula.setText("");
		                jTextNome.setText("");
		                jTextNacionalidade.setText("");
		                jTextCPF.setText("");
		                jTextMatricula.setEnabled(true);
		                jTextCPF.setEnabled(false);
		                jTextNome.setEnabled(true);
		                jTextNacionalidade.setEnabled(false);
		                botaoConsultar.setEnabled(true);
		                botaoGravar.setEnabled(false);
		                botaoDeletar.setEnabled(false);
		                jTextMatricula.requestFocus();
		            } else {
		                JOptionPane.showMessageDialog(janelaAutor, "Erro ao deletar a entrada!");
		            }
		        }
		    }
		});
		return janelaAutor;
	}
}
