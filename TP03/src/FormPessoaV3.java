
import javax.swing.*;
import java.awt.*;

public class FormPessoaV3 extends JFrame {

    Pessoa umaPessoa = new Pessoa();

    JTextField txtNumero = new JTextField("0");
    JTextField txtNome = new JTextField();

    JRadioButton rbM = new JRadioButton("Masculino");
    JRadioButton rbF = new JRadioButton("Feminino");

    ButtonGroup grupo = new ButtonGroup();

    JTextField txtIdade = new JTextField();

    private boolean cadastrada = false;

    public FormPessoaV3() {

        setTitle("Pessoa V3");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtNumero.setEditable(false);

        // Agrupamento dos botões de sexo
        grupo.add(rbM);
        grupo.add(rbF);

        JButton ok = new JButton("OK");
        JButton mostrar = new JButton("Mostrar");

        // Organização do formulário
        JPanel p = new JPanel(new GridLayout(5, 2, 5, 5));

        p.add(new JLabel("Número"));
        p.add(txtNumero);

        p.add(new JLabel("Nome"));
        p.add(txtNome);

        p.add(new JLabel("Sexo"));

        JPanel painelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSexo.add(rbM);
        painelSexo.add(rbF);
        p.add(painelSexo);

        p.add(new JLabel("Idade"));
        p.add(txtIdade);

        p.add(ok);
        p.add(mostrar);

        add(p);

        // Evento do botão OK
        ok.addActionListener(e -> salvar());

        // Evento do botão Mostrar
        mostrar.addActionListener(e -> mostrar());

        setVisible(true);
    }

    private void salvar() {

        try {

            String nome = txtNome.getText().trim();

            // Verifica se o nome foi preenchido
            if (nome.isEmpty()) {
                throw new Exception("Nome obrigatório.");
            }

            // Verifica se o sexo foi selecionado
            if (!rbM.isSelected() && !rbF.isSelected()) {
                throw new Exception("Selecione o sexo.");
            }

            char sexo = rbM.isSelected() ? 'M' : 'F';

            // Converte e valida a idade
            int idade = Integer.parseInt(txtIdade.getText().trim());

            if (idade <= 0) {
                throw new Exception("Digite uma idade válida.");
            }

            // Cadastra a pessoa apenas uma vez no contador
            if (!cadastrada) {

                umaPessoa.setPessoa(nome, sexo, idade);
                cadastrada = true;

            } else {

                // Atualiza os dados sem incrementar o contador
                umaPessoa.setNome(nome);
                umaPessoa.setSexo(sexo);
                umaPessoa.setIdade(idade);
            }

            // Atualiza o número exibido no formulário
            txtNumero.setText(String.valueOf(Pessoa.getKp()));

            JOptionPane.showMessageDialog(
                    this,
                    "Pessoa cadastrada com sucesso!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "A idade deve ser um número inteiro."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void mostrar() {

        if (umaPessoa.getNome() == null ||
                umaPessoa.getNome().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhuma pessoa cadastrada. Pressione OK primeiro."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Número: " + Pessoa.getKp()
                        + "\nNome: " + umaPessoa.getNome()
                        + "\nSexo: " + umaPessoa.getSexo()
                        + "\nIdade: " + umaPessoa.getIdade()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new FormPessoaV3());

    }
}
