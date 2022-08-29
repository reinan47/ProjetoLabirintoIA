package Interface;

import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import buscalargura.BuscaLargura;
import buscalargura.LinhaColuna;
import java.awt.SystemColor;
import javax.swing.JLabel;

import javax.swing.JPanel;

/**
 *
 * @author Reinan
 */
public class Painel extends JPanel{
    public Painel(){
        this.setLayout(null);
        this.setSize(1000,700);
        this.setBackground(SystemColor.YELLOW);
    }
   
    
    
    public static void main(String[] args) throws InterruptedException{
        JButton origem = new JButton();
        JButton []parede = new JButton[130];
        BuscaLargura bl = new BuscaLargura();
        JLabel titulo = new JLabel("BUSCA EM LARGURA");
        LinhaColuna[] caminho = (LinhaColuna[]) bl.BuscaLargura();
        
        Painel p = new Painel();
        
        JFrame ja = new JFrame("");
        ja.setBounds(0, 0, 1000, 700);
        ja.setLocationRelativeTo(null);
        ja.getContentPane().add(p);
        ja.setVisible(true);
        ja.setDefaultCloseOperation(ja.EXIT_ON_CLOSE);
        
 
        titulo.setBounds(420, 30, 200, 30);
        p.add(titulo);
        int cont = 0;
        for(int i = 0 ; i < 13 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(bl.tabuleiro[i][j] == 0){
                   parede[cont++] = new JButton();
                }
            }
        }
        cont = 0;
        for(int i = 0 ; i < 13 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(bl.tabuleiro[i][j] == 0){
                    
                    parede[cont].setBounds(75+(j*50), 100+(i*30), 50, 30);
                    parede[cont].setBackground(SystemColor.GREEN);
                    parede[cont].setBorderPainted(false);
                    p.add(parede[cont]);
                    p.revalidate();
                    ja.repaint();
                    //p.setContentPane(parede[cont]);
                    cont++;    
                }
            }
        }
        for(int i = 0 ; i < 110 ; i++){
            if(caminho[i] != null){
                origem.setBackground(SystemColor.BLACK);
                origem.setBounds(75+(caminho[i].getColuna()*50), 100+(caminho[i].getLinha()*30), 50, 30);
                TimeUnit.MILLISECONDS.sleep(200);
                origem.setBorderPainted(false);
                p.add(origem);
                p.revalidate();
                ja.repaint();
               // p.setContentPane(origem);
            }else{
                break;
            }
            
        }
    }
}

