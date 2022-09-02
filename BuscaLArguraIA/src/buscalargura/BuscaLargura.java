/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buscalargura;

import Interface.Painel;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;

/**
 *
 * @author Reinan
 */
public class BuscaLargura extends VerificarVisitados{
    private int boneco;
    private int objetivo;
    private int [] predecessor = new int [306];
    private int [] visitados = new int [306];
    private int [] caminho = new int [153];
    private int parede = 0;
    public int [][] tabuleiro = {
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0, 19, 20, 21, 22, 23, 24, 25,  26, 27, 28, 29, 0, 31, 32, 33,  0},
            {  0, 36,  0, 38,  0,  0,  0,  0,  0, 44,  0,  0,  0,  0,  0, 50,  0},
            {  0, 52,  0, 54, 55, 56,  0, 58, 59, 60, 61, 62, 63, 64,  0, 66,  0},
            {  0,  0,  0,  0,  0, 73,  0,  0,  0,  0,  0,  0,  0, 81,  0, 83,  0},
            {  0, 86, 87, 88, 89, 90,  0, 92, 93, 94,  0, 96, 97, 98, 99,100,  0},
            {  0,103,  0,105,  0,  0,  0,  0,  0,110,  0,112,  0,  0,  0,116,  0},
            {  0,119,  0,121,122,123,124,125,  0,127,128,129,  0,131,132,133,  0},
            {  0,135,  0,  0,  0,  0,140,  0,  0,143,  0,145,  0,  0,  0,  0,  0},
            {  0,152,  0,154,155,156,157,158,159,160,  0,162,163,164,165,166,  0},
            {  0,168,  0,170,  0,  0,  0,  0,  0,  0,  0,178,  0,  0,  0,  0,  0},
            {  0,185,186,187,  0,189,190,191,  0,193,194,195,  0,197,198,199,200},
            {  0,202,  0,  0,  0,206,  0,208,  0,210,  0,  0,  0,  0,  0,216,  0},  
            {  0,219,  0,221,222,223,  0,225,  0,227,228,229,230,231,232,233,  0},
            {  0,236,237,  0,  0,240,  0,242,  0,  0,  0,  0,  0,248,  0,  0,  0},
            {  0,  0,254,255,  0,257,  0,259,  0,261,262,263,  0,265,266,267,  0},
            {  0,270,  0,272,273,274,  0,276,277,278,  0,280,281,282,  0,284,  0},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}
        };

    public BuscaLargura() {
    }

    public void setBoneco(int boneco) {
        this.boneco = boneco;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public void setPredecessor(int[] predecessor) {
        this.predecessor = predecessor;
    }

    public void setVisitados(int[] visitados) {
        this.visitados = visitados;
    }

    public void setCaminho(int[] caminho) {
        this.caminho = caminho;
    }

    public void setParede(int parede) {
        this.parede = parede;
    }

    public int getBoneco() {
        return boneco;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public int[] getPredecessor() {
        return predecessor;
    }

    public int[] getVisitados() {
        return visitados;
    }

    public int[] getCaminho() {
        return caminho;
    }

    public int getParede() {
        return parede;
    }
    /**
     * @param args the command line arguments
     */
    public LinhaColuna[] BuscaLargura(){
        Queue<Integer> fila = new LinkedList<>();
        List<Integer> ListaCaminho = new LinkedList<>();
        LinhaColuna lc[] = new LinhaColuna[153];
        List<Integer> noExpandidos = new LinkedList<>();
        VerificarVisitados ListaVisitado = new VerificarVisitados();
        //origem do boneco
               
           int lin = (int) (Math.random()*18);
           int col = (int) (Math.random()*10);
           
        while(tabuleiro[lin][col] == 0){
           lin = (int) (Math.random()*18);
           col = (int) (Math.random()*10);
        }
        boneco = tabuleiro[lin][col];
            
        //origem do objetivo
        objetivo = tabuleiro[11][16];
        visitados[0]=boneco;
        
        for(int i = 0 ; i < 18 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                if(tabuleiro[i][j] < 10){
                    System.out.print("  " + tabuleiro[i][j] + " ");
                    continue;
                }
                if(tabuleiro[i][j] <= 99){
                    System.out.print(" " + tabuleiro[i][j] + " ");
                    continue;
                }
                System.out.print(tabuleiro[i][j]+" ");
            }
            System.out.println("\n");
        }
        int contadorVisitados = 0;
        visitados[0] = boneco;
        fila.add(boneco);
        while(fila.size() > 0){
            for(int i = 0 ; i < 18 ; i++){
                for(int j = 0 ; j < 17 ; j++){
                    //verificando se a posição inicial da lista qual sua referencia no tabuleiro
                    if(tabuleiro[i][j] == fila.peek()){
                        //verificar extremo de coluna negativa
                        if(j-1 == -1 || tabuleiro[i][j-1] == parede){
                            System.out.println("Não expande na esquerda");
                        }else{
                            //condição onde chama metodo de verificação dos estados ja alcançados
                            if(!ListaVisitado.visitado(tabuleiro[i][j-1], visitados)){
                                visitados[++contadorVisitados] = tabuleiro[i][j-1];
                                predecessor[contadorVisitados] = fila.peek();
                                noExpandidos.add(tabuleiro[i][j-1]);
                                fila.add(tabuleiro[i][j-1]); 
                            }
                        }
                        //verificar extremo de linha maxima
                        if(i+1 == 19 || tabuleiro[i+1][j] == parede){
                            System.out.println("Não expande em baixo");    
                        }else{
                            if(!ListaVisitado.visitado(tabuleiro[i+1][j], visitados)){
                                visitados[++contadorVisitados] = tabuleiro[i+1][j];
                                predecessor[contadorVisitados] = fila.peek();
                                noExpandidos.add(tabuleiro[i+1][j]);
                                fila.add(tabuleiro[i+1][j]); 
       
                            }
                        }
                        //verificar coluna maxima
                        if(j+1 == 17 || tabuleiro[i][j+1] == parede){
                            System.out.println("Não expande na direita"); 
                        }else{
                            if(!ListaVisitado.visitado(tabuleiro[i][j+1], visitados)){ 
                                visitados[++contadorVisitados] = tabuleiro[i][j+1]; 
                                predecessor[contadorVisitados] = fila.peek();
                                noExpandidos.add(tabuleiro[i][j+1]);
                                fila.add(tabuleiro[i][j+1]);  
                            }
                        }
                        //verificar linha negativa
                        if(i-1 == -1 || tabuleiro[i-1][j] == parede){
                            System.out.println("Não expande em cima"); 
                        }else{
                            if(!ListaVisitado.visitado(tabuleiro[i-1][j], visitados)){
                                visitados[++contadorVisitados] = tabuleiro[i-1][j];
                                predecessor[contadorVisitados] = fila.peek();
                                noExpandidos.add(tabuleiro[i-1][j]);
                                fila.add(tabuleiro[i-1][j]);  
                            }
                        }
                    }
                }
            }
            if(fila.peek() == objetivo){
                break;
            }
            //verificando se o ultimo no a ser expandido é o 
            //objetivo caso não for finalizar pois o objetivo é inalcançável
            if(fila.size() == 1 && fila.peek() != objetivo){
                System.out.println(fila.peek());
                System.out.println(fila);
                fila.poll();
                System.out.println("Objetivo inalcançável");
                System.exit(0);
            }
            System.out.println("[" + fila.peek() + "]" + "-> Expandiu ->" + noExpandidos);
            noExpandidos.removeAll(noExpandidos);
            
 
            System.out.println("Removeu -> [" + fila.poll() + "]");
            System.out.println("Fila atual -> " + fila + "\n");
            

        }
        
        System.out.println("\nObjetivo envontrado. Fila final -> " + fila + "\n");
        //Apos gerar os caminhos verifico a lista de visitado e a dos predecessor 
        //e gero uma terceira lista pra gerar o caminho mais rapido até o objetivo
        int contador = 1;
        caminho[0] = objetivo;
        for(int a = 0 ; a < 221 ; a++){
            if(visitados[a] == objetivo){
                caminho[contador++]=predecessor[a];
                objetivo = predecessor[a];
                a = 0;
            }  
        }
        for(int a = 0 ; a < 110 ; a++){
            if(caminho[a] == 0)
                break;
            ListaCaminho.add(0,caminho[a]);
        }
        System.out.println("Caminho -> "+ListaCaminho);
        contador = 0;
        //pegando os indices do caminho percorrido usando busca em largura na matriz
        for(Integer elemento : ListaCaminho){
            for(int i = 0 ; i < 18 ; i++){
                for(int j = 0 ; j < 17 ; j++){
                    if(tabuleiro[i][j] == elemento){
                        lc[contador++] = new LinhaColuna(i, j);
                    }
                }
            }
        }
        return lc;
    } 
}
