import javax.swing.*;
import java.awt.*;

public class CalculadoraForm extends JFrame {

    private JTextField txtN1;
    private JTextField txtN2;
    private JTextField txtResultado;

    public CalculadoraForm() {

        setTitle("Calculadora");
        setSize(350,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtN1 = new JTextField();
        txtN2 = new JTextField();
        txtResultado = new JTextField();
        txtResultado.setEditable(false);

        JButton soma = new JButton("+");
        JButton sub = new JButton("-");
        JButton mult = new JButton("*");
        JButton div = new JButton("/");
        JButton clear = new JButton("C");

        JPanel painel = new JPanel(new GridLayout(5,2,5,5));

        painel.add(new JLabel("Número 1"));
        painel.add(txtN1);

        painel.add(new JLabel("Número 2"));
        painel.add(txtN2);

        painel.add(new JLabel("Resultado"));
        painel.add(txtResultado);

        painel.add(soma);
        painel.add(sub);
        painel.add(mult);
        painel.add(div);

        add(painel, BorderLayout.CENTER);
        add(clear, BorderLayout.SOUTH);

        soma.addActionListener(e -> calcular("+"));
        sub.addActionListener(e -> calcular("-"));
        mult.addActionListener(e -> calcular("*"));
        div.addActionListener(e -> calcular("/"));

        clear.addActionListener(e -> {
            txtN1.setText("");
            txtN2.setText("");
            txtResultado.setText("");
        });

        setVisible(true);
    }

    private void calcular(String op){

        try{

            double n1 = Double.parseDouble(txtN1.getText());
            double n2 = Double.parseDouble(txtN2.getText());

            double r=0;

            switch(op){

                case "+":
                    r=n1+n2;
                    break;

                case "-":
                    r=n1-n2;
                    break;

                case "*":
                    r=n1*n2;
                    break;

                case "/":

                    if(n2==0)
                        throw new ArithmeticException("Divisão por zero!");

                    r=n1/n2;
                    break;
            }

            txtResultado.setText(String.valueOf(r));

        }catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(this,"Digite números válidos!");

        }catch(ArithmeticException ex){

            JOptionPane.showMessageDialog(this,ex.getMessage());

        }finally{

            System.out.println("Operação finalizada.");

        }

    }

}
