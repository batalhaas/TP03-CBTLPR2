import javax.swing.*;
import java.awt.*;

public class FormPessoaV1 extends JFrame {

    Pessoa umaPessoa = new Pessoa();

    JTextField txtNumero=new JTextField("0");
    JTextField txtNome=new JTextField();
    JTextField txtSexo=new JTextField();
    JTextField txtIdade=new JTextField();

    public FormPessoaV1(){

        setTitle("Pessoa V1");
        setSize(350,250);
        setLocationRelativeTo(null);

        txtNumero.setEditable(false);

        JButton ok=new JButton("OK");
        JButton mostrar=new JButton("Mostrar");

        JPanel p=new JPanel(new GridLayout(5,2));

        p.add(new JLabel("Número"));
        p.add(txtNumero);

        p.add(new JLabel("Nome"));
        p.add(txtNome);

        p.add(new JLabel("Sexo"));
        p.add(txtSexo);

        p.add(new JLabel("Idade"));
        p.add(txtIdade);

        p.add(ok);
        p.add(mostrar);

        add(p);

        ok.addActionListener(e->salvar());
        mostrar.addActionListener(e->mostrar());

        setVisible(true);

    }

    private void salvar(){

        try{

            String nome=txtNome.getText();

            char sexo=txtSexo.getText().toUpperCase().charAt(0);

            int idade=Integer.parseInt(txtIdade.getText());

            if(nome.isEmpty())
                throw new Exception("Nome obrigatório.");

            if(sexo!='M' && sexo!='F')
                throw new Exception("Sexo deve ser M ou F.");

            umaPessoa.setPessoa(nome,sexo,idade);

            txtNumero.setText(String.valueOf(Pessoa.getKp()));

        }catch(Exception ex){

            JOptionPane.showMessageDialog(this,ex.getMessage());

        }

    }

    private void mostrar(){

        JOptionPane.showMessageDialog(this,
                "Número: "+Pessoa.getKp()+
                "\nNome: "+umaPessoa.getNome()+
                "\nSexo: "+umaPessoa.getSexo()+
                "\nIdade: "+umaPessoa.getIdade());

    }

}