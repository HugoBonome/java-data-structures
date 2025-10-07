package esd;

import java.util.Arrays;

public class ListaSequencialOrdenada <T extends Comparable> {

    final int MinSubseqLen = 31;

    Comparable[] area;
    int len = 0;
    final int defcap = 8;

    @SuppressWarnings("unchecked")
    public ListaSequencialOrdenada() {
        area = new Comparable[defcap];
    }

    @SuppressWarnings("unchecked")
    public void expande(int len) {
        T[] novaLista = (T[])new Comparable[capacidade()*2];
        for (int i = 0; i < len; i++){
            novaLista[i] = (T) area[i];
        }
        area = (T[]) novaLista;
    }


    void expande() {
        T[] novaLista = (T[])new Comparable[capacidade()*2];
        for (int i = 0; i < len; i++){
            novaLista[i] = (T) area[i];
        }
        area = (T[]) novaLista;
    }

    public boolean esta_vazia() {
        return (len == 0);
    }

    public int capacidade() {
        return area.length;
    }


    public void remove(T valor) {
        // remove o valor, porém preservando o ordenamento da lista
        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");

        int indice = procura(valor);

        if(indice >= 0 && indice < len){
            for(int i = indice; i < len - 1; i++){
                area[i] = area[i + 1];
            }
            area[len - 1] = null;
            len--;
        } else {
            throw new IndexOutOfBoundsException("Indice Invalido");
        }
    }

    @SuppressWarnings("unchecked")
    public T obtem(int indice) {
        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");
        if(indice >= 0 && indice < len){
            return (T) area[indice];
        } else{
            throw new IndexOutOfBoundsException("Indice Invalido");
        }
    }

    public int comprimento() {
        return len;
    }

    public void limpa() {
        for(int i = 0; i < len; i++){
            area[i] = null;
        }
        len = 0;
    }

    @SuppressWarnings("unchecked")
    public void insere(T elemento) {
        // insere o valor na lista, preservando seu ordenamento
        if (len >= area.length) expande();

        for(int i = 0; i < + len; i++){
            if( elemento.compareTo(area[i]) <= 0){
                insereNaLista(i, elemento);
                return;
            }
        }
        area[len++] = elemento;


    }

    public void insereNaLista(int indice, T elemento){
        for (int i = len; i > indice; i--) {
            area[i] = area[i - 1];
        }
        area[indice] = elemento;
        len++;
    }

    public int procura(T valor) {
        // procura o valor dentro da lista usando busca binária
        // retorna a posição onde se encontra, ou -1 caso não exista
//            for (int i = 0; i < len; i++) {
//                if (area[i].equals(valor)) {
//                    return i;
//                }
//            }

        if (len == 0) return -1;

        int inicio = 0;
        int fim = len - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; // Evita overflow

            if (area[meio].compareTo(valor) == 0) {
                return meio; // Valor encontrado
            }

            if (area[meio].compareTo(valor) > 0 ) {
                fim = meio - 1; // Pesquisar na metade esquerda
            } else {
                inicio = meio + 1; // Pesquisar na metade direita
            }
        }
            return -1;
    }

    @Override
    public String toString() {
        return "ListaSequencialOrdenada{" +
                "area=" + Arrays.toString(area) +
                ", len=" + len +
                '}';
    }
}
