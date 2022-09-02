package Interface;

import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import buscalargura.BuscaLargura;
import buscalargura.LinhaColuna;
import java.awt.SystemColor;
import static java.lang.System.exit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

/**
 *
 * @author Reinan
 */
public class Painel extends JPanel{
    JButton b = new JButton("SAIR");
    public Painel(){
        this.setLayout(null);
        this.setSize(1000,700);
        this.setBackground(SystemColor.YELLOW);
        b.setBounds(450, 600, 100, 40);
        b.setBackground(SystemColor.LIGHT_GRAY);
        b.setBorderPainted(false);
        add(b);
        
        
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
    }
   
    public static void main(String[] args) throws InterruptedException{
        JButton origem = new JButton();
        JButton []parede = new JButton[250];
        BuscaLargura bl = new BuscaLargura();
        JLabel titulo = new JLabel("BUSCA EM LARGURA");
        LinhaColuna[] caminho = bl.BuscaLargura();
 
        Painel p = new Painel();
        JFrame ja = new JFrame("");
        
        
        p.setBounds(0, 0, 50, 800);
        ja.setBounds(0, 0, 1000, 700);
        ja.setLocationRelativeTo(null);
        ja.getContentPane().add(p);
        ja.setVisible(true);
        ja.setDefaultCloseOperation(ja.EXIT_ON_CLOSE);
        
 
        titulo.setBounds(440, 10, 200, 30);
        p.add(titulo);
        
        int cont = 0;
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(bl.tabuleiro[i][j] == 0){
                   parede[cont++] = new JButton();
                }
            }
        }
        cont = 0;
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(bl.tabuleiro[i][j] == 0){
                    parede[cont].setBounds(70+(j*50), 40+(i*30), 50, 30);
                    parede[cont].setBackground(SystemColor.GREEN);
                    parede[cont].setBorderPainted(false);
                    p.add(parede[cont]);
                    p.revalidate();
                    ja.repaint();
                    cont++;    
                }
            }
        }
        
        boolean aux = true;
        for(int i = 0 ; i < 110 ; i++){
            if(caminho[i] != null){
                origem.setBackground(SystemColor.BLACK);
                origem.setBounds(70+(caminho[i].getColuna()*50), 40+(caminho[i].getLinha()*30), 50, 30);
                TimeUnit.MILLISECONDS.sleep(500);
                origem.setBorderPainted(false);
                p.add(origem);
                p.revalidate();
                ja.repaint();
                if(aux){
                    TimeUnit.SECONDS.sleep(2);
                    aux = false;
                }     
            }else{
                break;
            }
            
        }
        
        JOptionPane.showMessageDialog(null, "IA achou o caminho.");
        exit(0);
    }
}

