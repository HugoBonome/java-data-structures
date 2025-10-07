package esd;

import java.util.Arrays;

public class ListaSequencialSimples <T> {
    protected T area[];
    protected int len;
    final int DEFCAP = 8;

    @SuppressWarnings("unchecked")
    public ListaSequencialSimples() {
        area = (T[])new Object[DEFCAP];
        this.len = 0;
    }

    public void adiciona(T valor)  {
        if(len >= area.length){
            expande();
        }
        area[len] = valor;
        len++;
    }

    @SuppressWarnings("unchecked")
    public void expande(){
        T[] novaLista = (T[])new Object[capacidade()*2];
        for (int i = 0; i < len; i++){
            novaLista[i] = area[i];
        }
        area = (T[]) novaLista;
    }

    public void remove(int indice){
        // remove um valor da posição indicada pelo parâmetro "indice"
        // move para essa posição o valor que está no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");
        if(indice >= 0 && indice < len){
//            for(int i = indice; i < len - 1; i++){
//                area[i] = area[i + 1];
//            }
//            area[len - 1] = null;
//            len--;
            T item = area[len -1];
            area[indice] = item;
            area[len -1] = null;
            len--;
        } else {
            throw new IndexOutOfBoundsException("Indice Invalido");
        }

    }

    public T obtem(int indice){
        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");
        if(indice >= 0 && indice < len){
            return area[indice];
        } else{
            throw new IndexOutOfBoundsException("Indice Invalido");
        }
    }

    public int comprimento(){
        return len;
    }

    public void limpa(){
        for(int i = 0; i < len; i++){
            area[i] = null;
        }
        len = 0;
    }

    public int procura(T valor) {
        for (int i = 0; i < len; i++) {
            if (area[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public void substitui(int indice, T valor) {
        // armazena o valor na posição indicada por "indice", substituindo o valor lá armazenado atualmente
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        if(indice >= 0 && indice < len){
            area[indice] = valor;
        } else{
            throw new IndexOutOfBoundsException("Indice Invalido");
        }
    }

    public int capacidade(){
        return area.length;
    }

    @Override
    public String toString() {
        return "ListaSequencialSimples{" +
                "area=" + Arrays.toString(area) +
                '}';
    }

    public boolean esta_vazia(){
        return len == 0;
    }
}