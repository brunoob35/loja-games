import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
public class Janela extends JFrame {
 public Janela() {
 // Configuração da janela
 setTitle("Janela Games");
 setSize(500, 500);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setLocationRelativeTo(null); // Centraliza na tela
 // Criando componentes
 JLabel label = new JLabel("Bem-vindo ao Janela Games!");
 JButton botao = new JButton("Clique Aqui");
 // Adicionando componentes à janela
 setLayout(new GridLayout(3,2));
 add(label);
 add(botao);
 }
 public static void main(String[] args) {
 // Criando e exibindo a janela
 SwingUtilities.invokeLater(() -> {
 Janela janela = new Janela();
 janela.setVisible(true);
 });
 }
}