import javax.swing.*;
import java.awt.*;

class Monitoramento extends JFrame {
    private JLabel productionLabel;
    private JLabel packagingLabel;
    private JLabel inspectionLabel;
    private JLabel deliveryLabel;

    public Monitoramento() {
        setTitle("Visualização em Tempo Real");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 1));

        productionLabel = new JLabel("Produção: 0");
        packagingLabel = new JLabel("Embalagem: 0");
        inspectionLabel = new JLabel("Inspeção: 0");
        deliveryLabel = new JLabel("Entrega: 0");

        add(productionLabel);
        add(packagingLabel);
        add(inspectionLabel);
        add(deliveryLabel);

        setLocationRelativeTo(null);
        setVisible(false);
    }

    public void updateData(int production, int packaging, int inspection, int delivery) {
        productionLabel.setText("Produção: " + production);
        packagingLabel.setText("Embalagem: " + packaging);
        inspectionLabel.setText("Inspeção: " + inspection);
        deliveryLabel.setText("Entrega: " + delivery);
    }
}
