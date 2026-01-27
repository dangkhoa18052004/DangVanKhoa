import javax.swing.*;
import java.awt.*;

public class PaymentForm extends JFrame {
    private final JRadioButton rMale = new JRadioButton("Male");
    private final JRadioButton rFemale = new JRadioButton("Female");
    private final JRadioButton rChild = new JRadioButton("Child (0 - 17 years)");

    private final JTextField txtAge = new JTextField(10);
    private final JTextField txtPayment = new JTextField(10);

    private final JButton btnCalculate = new JButton("Calculate");

    public PaymentForm() {
        setTitle("Calculate the Payment for the Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chỉ chọn 1 trong 3
        ButtonGroup group = new ButtonGroup();
        group.add(rMale);
        group.add(rFemale);
        group.add(rChild);

        // Chọn mặc định để không bị trống
        rMale.setSelected(true);

        // Payment chỉ hiển thị
        txtPayment.setEditable(false);

        // Layout
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;

        // Row 1
        g.gridx = 0;
        g.gridy = 0;
        p.add(rMale, g);
        g.gridx = 1;
        p.add(rFemale, g);
        g.gridx = 2;
        p.add(rChild, g);

        // Row 2
        g.gridx = 0;
        g.gridy = 1;
        p.add(new JLabel("Age (Years)"), g);
        g.gridx = 1;
        p.add(txtAge, g);
        g.gridx = 2;
        p.add(btnCalculate, g);

        // Row 3
        g.gridx = 0;
        g.gridy = 2;
        p.add(new JLabel("Payment is"), g);
        g.gridx = 1;
        p.add(txtPayment, g);
        g.gridx = 2;
        p.add(new JLabel("euro \u20AC"), g);

        // Xử lý khi bấm Calculate
        btnCalculate.addActionListener(e -> onCalculate());

        setContentPane(p);
        pack();
        setLocationRelativeTo(null);
    }

    private void onCalculate() {
        try {
            int age = readAgeOrThrow();

            PaymentCalculator.Type type;
            if (rChild.isSelected())
                type = PaymentCalculator.Type.CHILD;
            else if (rMale.isSelected())
                type = PaymentCalculator.Type.MALE;
            else
                type = PaymentCalculator.Type.FEMALE;

            int payment = PaymentCalculator.calculate(type, age);
            txtPayment.setText(String.valueOf(payment));

        } catch (IllegalArgumentException ex) {
            txtPayment.setText("");
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            txtAge.requestFocus();
            txtAge.selectAll();
        }
    }

    private int readAgeOrThrow() {
        String s = txtAge.getText().trim();
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Age is required.");
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Age must be a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentForm().setVisible(true));
    }
}
