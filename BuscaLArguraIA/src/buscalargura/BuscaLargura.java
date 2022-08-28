/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buscalargura;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Reinan
 */
public class BuscaLargura extends VerificarVisitados{
    private int boneco;
    private int objetivo;
    //int [][] tabuleiro = new int [13][17];
    private int [] predecessor = new int [221];
    private int [] visitados = new int [221];
    private int [] caminho = new int [110];
    private int parede = 0;

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
    int[] BuscaLargura(){
        Queue<Integer> fila = new LinkedList<>();
        List<Integer> ListaCaminho = new LinkedList<>();
        LinhaColuna lc[] = new LinhaColuna[110];

        int [][] tabuleiro = {
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 18, 19, 20, 21, 22, 23, 24, 25,  0, 27, 28, 29, 30, 31, 32, 33,  0},
            {  0, 36,  0, 38,  0,  0,  0,  0,  0, 44,  0,  0,  0,  0,  0, 50,  0},
            {  0, 52,  0, 54, 55, 56,  0, 58, 59, 60, 61, 62, 63, 64,  0, 66,  0},
            {  0,  0,  0,  0,  0, 73,  0,  0,  0,  0,  0,  0,  0, 81,  0, 83,  0},
            {  0, 86, 87, 88, 89, 90,  0, 92, 93, 94,  0, 96, 97, 98,  0,100,  0},
            {  0,103,  0,105,  0,  0,  0,  0,  0,110,  0,112,  0,  0,  0,116,  0},
            {  0,119,  0,121,122,123,124,125,  0,127,  0,129,  0,131,132,133,  0},
            {  0,135,  0,  0,  0,  0,  0,  0,  0,143,  0,145,  0,147,  0,  0,  0},
            {  0,152,  0,154,155,156,157,158,159,160,  0,162,  0,164,165,166,  0},
            {  0,168,  0,170,  0,  0,  0,174,  0,  0,  0,178,  0,  0,  0,182,  0},
            {  0,185,186,187,  0,189,190,191,192,193,194,195,  0,197,198,199,200},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}    
        };
        VerificarVisitados ListaVisitado = new VerificarVisitados();

        /*int cont = 1;
        
        for(int i = 0 ; i < 13 ; i++){
            for(int j = 0 ; j < 17 ; j++){
                tabuleiro[i][j] = cont++;
            }
        }*/
        //origem do boneco
        boneco = tabuleiro[1][0];
        //origem do objetivo
        objetivo = tabuleiro[11][16];
        visitados[0]=boneco;
        for(int i = 0 ; i < 13 ; i++){
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
            for(int i = 0 ; i < 13 ; i++){
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
                                fila.add(tabuleiro[i][j-1]); 
                            }
                        }
                        //verificar extremo de linha maxima
                        if(i+1 == 13 || tabuleiro[i+1][j] == parede){
                            System.out.println("Não expande em baixo");    
                        }else{
                            if(!ListaVisitado.visitado(tabuleiro[i+1][j], visitados)){
                                visitados[++contadorVisitados] = tabuleiro[i+1][j];
                                predecessor[contadorVisitados] = fila.peek();
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
                                fila.add(tabuleiro[i-1][j]);  
                            }
                        }
                    }
                }
            }
            if(fila.peek() == objetivo){
               // fila.add(objetivo);
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
            System.out.println("Expandir = " + fila.peek());
            System.out.println(fila);
            fila.poll();
            

        }
        System.out.println(fila);
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
        System.out.println("\n"+ListaCaminho);
        contador = 0;
        //pegando os indices do caminho percorrido usando busca em largura na matriz
        for(Integer elemento : ListaCaminho){
            for(int i = 0 ; i < 13 ; i++){
                for(int j = 0 ; j < 17 ; j++){
                    if(tabuleiro[i][j] == elemento){
                        lc[contador++] = new LinhaColuna(i, j);
                    }
                }
            }
        }
        System.out.println(lc[0].getColuna());
        return caminho;
    }
    
}
