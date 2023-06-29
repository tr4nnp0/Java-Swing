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

import br.senac.rj.banco.modelo.Livro;
/**
 * Essa classe define as informações e medidas para a janela e Menu de livros.
 * @author Alexandre
 * @author Gleison
 * @author Victor
 * @author Arthur
 */
public class JanelaLivro {
	/**
	 *	 Esse método define os parametros das Janelas para cadastro de livros
	 */
	public static JFrame criarJanelaLivro() {
		// Define a janela
		JFrame janelaLivro = new JFrame("Atualiza��o de livro"); // Janela Normal
		janelaLivro.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaLivro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaLivro.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaLivro.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCodigo = new JLabel("Codigo: ");
		JLabel labelTitulo = new JLabel("Titulo do Livro: ");
		JLabel labelAnoLancamento = new JLabel("AnoLancamento: ");
		JLabel labelPreco = new JLabel("Preco do produto: ");
		// Posiciona os labels na janela
		labelCodigo.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelTitulo.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelAnoLancamento.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelPreco.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextCodigo = new JTextField();
		JTextField jTextPreco = new JTextField();
		JTextField jTextTitulo = new JTextField();
		JTextField jTextAnoLancamento = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCodigo.setEnabled(true);
		jTextTitulo.setEnabled(true);
		jTextPreco.setEnabled(false);
		jTextAnoLancamento.setEnabled(false);
		// Posiciona os input box
		jTextCodigo.setBounds(180, 40, 50, 20);
		jTextTitulo.setBounds(180, 80, 50, 20);
		jTextPreco.setBounds(180, 120, 150, 20);
		jTextAnoLancamento.setBounds(180, 160, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaLivro.add(labelCodigo);
		janelaLivro.add(labelTitulo);
		janelaLivro.add(labelAnoLancamento);
		janelaLivro.add(labelPreco);
		janelaLivro.add(jTextCodigo);
		janelaLivro.add(jTextTitulo);
		janelaLivro.add(jTextAnoLancamento);
		janelaLivro.add(jTextPreco);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janelaLivro.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(30, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaLivro.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(275, 200, 100, 20);
		janelaLivro.add(botaoLimpar);
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(155, 200, 100, 20);
		janelaLivro.add(botaoDeletar);
		botaoDeletar.setEnabled(false);
		// Define objeto conta para pesquisar no banco de dados
		Livro livro = new Livro();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = Integer.parseInt(jTextCodigo.getText());
					String titulo = jTextTitulo.getText();
					if (jTextCodigo.getText().isEmpty() || jTextTitulo.getText().isEmpty()) {
			                JOptionPane.showMessageDialog(janelaLivro, "Preencha os campos codigo e titulo do livro corretamente!!");
			                return;
			        } //O programa não estava pegando esse erro de campo vazio na String
					botaoGravar.setEnabled(true);
					int anoLancamento;
					int preco;
					if (!livro.consultarLivro(codigo, titulo))
						anoLancamento = 0;
					else 
						anoLancamento = livro.getAnoLancamento();
						jTextAnoLancamento.setText(String.valueOf(anoLancamento));
						jTextCodigo.setEnabled(false);
						jTextTitulo.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextAnoLancamento.setEnabled(true);
						jTextAnoLancamento.requestFocus();
					if (!livro.consultarLivro(codigo, titulo)) 
						preco = 0;
					else 
						preco = livro.getPreco();
						jTextPreco.setText(String.valueOf(preco));
						jTextCodigo.setEnabled(false);
						jTextTitulo.setEnabled(false);
						botaoConsultar.setEnabled(false);
						botaoDeletar.setEnabled(true);
						jTextPreco.setEnabled(true);
						jTextPreco.requestFocus();
				} catch (NumberFormatException erro) {
					JOptionPane.showMessageDialog(janelaLivro,
							"Preencha os campos codigo e titulo do livro corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaLivro, "Deseja atualizar?", "Confirma��o",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
						int codigo;
			            String titulo;
			            int anoLancamento;
			            int preco;
			            try {
			                codigo = Integer.parseInt(jTextCodigo.getText());
			                titulo = jTextTitulo.getText().trim();
			                String anoLancamentoText = jTextAnoLancamento.getText().trim();
			                String precoText = jTextPreco.getText().trim();

			                if (anoLancamentoText.isEmpty() || precoText.isEmpty()) {
			                    JOptionPane.showMessageDialog(janelaLivro, "Preencha todos os campos com valores válidos");
			                    return;
			                }
			                anoLancamento = Integer.parseInt(anoLancamentoText);
			                preco = Integer.parseInt(precoText);
			            	} catch (NumberFormatException ex) {
			                JOptionPane.showMessageDialog(janelaLivro, "Preencha todos os campos com valores válidos");
			               return;
			            }				
						if (!livro.consultarLivro(codigo, titulo)) {
							if (!livro.cadastrarLivro(codigo, titulo, anoLancamento, preco))
								JOptionPane.showMessageDialog(janelaLivro, "Erro na inclus�o do livro!");
							else
								JOptionPane.showMessageDialog(janelaLivro, "Inclus�o realizada!");
						} else {
							if (!livro.atualizarDadosLivro(codigo, titulo, anoLancamento, preco))
								JOptionPane.showMessageDialog(janelaLivro, "Erro na atualiza��o do livro!");
							else
								JOptionPane.showMessageDialog(janelaLivro, "Atualização realizada!");
						}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCodigo.setText(""); // Limpar campo
				jTextPreco.setText(""); // Limpar campo
				jTextTitulo.setText(""); // Limpar campo
				jTextAnoLancamento.setText(""); // Limpar campo
				jTextCodigo.setEnabled(true);
				jTextPreco.setEnabled(false);
				jTextTitulo.setEnabled(true);
				jTextAnoLancamento.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextCodigo.requestFocus(); // Colocar o foco em um campo
			}
		});
		
		botaoDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaLivro, "Deseja deletar?", "Confirmação", JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            int codigo = Integer.parseInt(jTextCodigo.getText());
		            String titulo = jTextTitulo.getText().trim();
					int anoLancamento = Integer.parseInt(jTextAnoLancamento.getText());
					int preco = Integer.parseInt(jTextPreco.getText());		            
		            if (livro.deletarDadosLivro(codigo, titulo, anoLancamento, preco)) {
		                JOptionPane.showMessageDialog(janelaLivro, "Dados deletados com sucesso!");
		                jTextCodigo.setText("");
		                jTextTitulo.setText("");
		                jTextAnoLancamento.setText("");
		                jTextPreco.setText("");
		                jTextCodigo.setEnabled(true);
		                jTextPreco.setEnabled(false);
		                jTextTitulo.setEnabled(true);
		                jTextAnoLancamento.setEnabled(false);
		                botaoConsultar.setEnabled(true);
		                botaoGravar.setEnabled(false);
		                botaoDeletar.setEnabled(false);
		                jTextCodigo.requestFocus();
		            } else {
		                JOptionPane.showMessageDialog(janelaLivro, "Erro ao deletar a entrada!");
		            }
		        }
		    }
		});
		return janelaLivro;
	}
}
