package buscalargura;

/**
 *
 * @author Reinan
 */
public class VerificarVisitados {
    boolean visitado(int elementoLista, int []visitado){
        for(int a = 0 ; a < 221 ; a++){
            if(elementoLista == visitado[a]){
                return true;
            }
        }
        return false;
    }
}
