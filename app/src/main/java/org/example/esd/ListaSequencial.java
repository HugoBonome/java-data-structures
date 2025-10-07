package esd;

import java.util.Arrays;

public class ListaSequencial <T> {
    protected T area[];
    protected int len;
    final int DEFCAP = 8;

    @SuppressWarnings("unchecked")
    public ListaSequencial() {
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

    public void insere(int indice, T elemento) {
        // insere um valor na posição indicada por "indice"
        // dispara IndexOutOfBoundsException se "indice" for inválido
        if (len >= area.length) expande();
        if (indice < 0 || indice > len) throw new IndexOutOfBoundsException("Indice Invalido");

        for (int i = len; i > indice; i--) {
            area[i] = area[i - 1];
        }
        area[indice] = elemento;
        len++;

    }

    public void remove(int indice){
        // remove um valor da posição indicada pelo parâmetro "indice"
        // move para essa posição o valor que está no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");

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

    public void remove_ultimo() {
        // remove o último valor da lista
        // disparar uma exceção IndexOutOfBoundsException caso lista vazia
        if (esta_vazia()) throw new IndexOutOfBoundsException("Lista vazia");
        area[len - 1] = null;
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

    public void ordena(){
//        Selection Sort
//        for (int j = 0; j < comprimento() - 1; j++) {
//            int menor = j;
//            for (int k = j + 1; k < comprimento(); k++) {
//                Comparable<T> atual = (Comparable<T>) obtem(k);
//                if (atual.compareTo(obtem(menor)) < 0) {
//                    menor = k;
//                }
//            }
//            T temp = obtem(j);
//            substitui(j, obtem(menor));
//            substitui(menor, temp);
//        }
//        Merge Sort
        if (len > 1){
            ordena_mescla(0, comprimento() -1);
        }


    }

    public void ordena_mescla(int inicio, int fim){

        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            ordena_mescla(inicio, meio);
            ordena_mescla(meio + 1, fim);
            mescla(inicio, meio, fim);
        }

    }

    private void mescla(int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        // Criar arrays temporários
        ListaSequencial<T> esquerda = new ListaSequencial<>();
        ListaSequencial<T> direita = new ListaSequencial<>();

        // Copiar dados para os arrays temporários
        for (int i = 0; i < n1; i++) {
            esquerda.adiciona(obtem(inicio + i));
        }
        for (int j = 0; j < n2; j++) {
            direita.adiciona(obtem(meio + 1 + j));
        }

        // Intercalar os arrays temporários de volta na lista original
        int i = 0, j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            Comparable<T> elemEsquerda = (Comparable<T>) esquerda.obtem(i);
            T elemDireita = direita.obtem(j);

            if (elemEsquerda.compareTo(elemDireita) <= 0) {
                substitui(k, esquerda.obtem(i));
                i++;
            } else {
                substitui(k, direita.obtem(j));
                j++;
            }
            k++;
        }

        // Copiar os elementos restantes, se houver
        while (i < n1) {
            substitui(k, esquerda.obtem(i));
            i++;
            k++;
        }
        while (j < n2) {
            substitui(k, direita.obtem(j));
            j++;
            k++;
        }
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
