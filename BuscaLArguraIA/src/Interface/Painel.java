package Interface;

import java.awt.Color;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import buscalargura.BuscaLargura;
import buscalargura.LinhaColuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Reinan
 */
public class Painel extends JFrame{
    JLabel jl = new JLabel("BUSCA EM LARGURA");
    public Painel(){
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.YELLOW);
        setContentPane(p); 
    }
   
    
    
    public static void main(String[] args) throws InterruptedException{
        JButton origem = new JButton();
        JButton []parede = new JButton[130];
        BuscaLargura bl = new BuscaLargura();
        LinhaColuna[] caminho = (LinhaColuna[]) bl.BuscaLargura();
        
        Painel p = new Painel();
        p.setVisible(true);
  
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
                    TimeUnit.MILLISECONDS.sleep(10);
                    parede[cont].setBackground(Color.GREEN);
                    p.setContentPane(parede[cont]);
                    cont++;    
                }
            }
        }
        for(int i = 0 ; i < 110 ; i++){
            if(caminho[i] != null){
                origem.setBackground(Color.BLACK);
                origem.setBounds(75+(caminho[i].getColuna()*50), 100+(caminho[i].getLinha()*30), 50, 30);
                TimeUnit.MILLISECONDS.sleep(200);
                p.setContentPane(origem);
            }else{
                break;
            }
            
        }
    }
}

