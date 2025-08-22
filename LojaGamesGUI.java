import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.math.BigDecimal;


public class LojaGamesGUI extends JFrame {
    private Cliente cliente;
    private DefaultListModel<Game> listaGamesModel;
    private DefaultListModel<Game> carrinhoModel;
    private JLabel saldoLabel;

    public LojaGamesGUI(Cliente cliente, ArrayList<Game> gamesDisponiveis) {
        this.cliente = cliente;

        setTitle("Loja de Games - Gestio Games");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Lista de games
        listaGamesModel = new DefaultListModel<>();
        for (Game g : gamesDisponiveis) {
            listaGamesModel.addElement(g);
        }
        JList<Game> listaGames = new JList<>(listaGamesModel);
        JScrollPane scrollGames = new JScrollPane(listaGames);

        // Lista de carrinho
        carrinhoModel = new DefaultListModel<>();
        JList<Game> listaCarrinho = new JList<>(carrinhoModel);
        JScrollPane scrollCarrinho = new JScrollPane(listaCarrinho);

        // Painel de botões
        JButton comprarBtn = new JButton("Comprar");
        JButton addFundosBtn = new JButton("Adicionar Fundos");

        saldoLabel = new JLabel("Saldo: R$ " + cliente.getSaldo());

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(comprarBtn);
        botoesPanel.add(addFundosBtn);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Carrinho:"), BorderLayout.NORTH);
        rightPanel.add(scrollCarrinho, BorderLayout.CENTER);
        rightPanel.add(saldoLabel, BorderLayout.SOUTH);

        add(new JLabel("Games Disponíveis:"), BorderLayout.NORTH);
        add(scrollGames, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);

        // Eventos
        comprarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game selecionado = listaGames.getSelectedValue();
                if (selecionado != null) {
                    if (cliente.getSaldo().compareTo(selecionado.getPreco()) >= 0) {
                        cliente.comprarGame(selecionado);
                        carrinhoModel.addElement(selecionado);
                        saldoLabel.setText("Saldo: R$ " + cliente.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
                    }
                }
            }
        });

        addFundosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorStr = JOptionPane.showInputDialog("Digite o valor a adicionar:");
                try {
                    double valor = Double.parseDouble(valorStr);
                    cliente.adicionarFundos(BigDecimal.valueOf(valor));
                    saldoLabel.setText("Saldo: R$ " + cliente.getSaldo());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido!");
                }
            }
        });

        setVisible(true);
    }
}
