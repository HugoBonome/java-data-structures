package esd;

import java.util.Arrays;

public class Deque <T>{
    T[] area;
    int inicio = 0, fim = 0;
    int len = 0;

    public Deque() {
        this.area = ((T[]) new Object[10]);
    }

    public int comprimento() {
        return len;
    }

    public int capacidade() {
        return this.area.length;
    }

    public void adiciona(T algo) {
        if (this.comprimento() == this.capacidade()) {
            expandirArray();
        }
        this.area[this.fim] = algo;
        len++;
        fim = (fim + 1) % area.length;
    }

    public void insere(T algo) {
        if (this.comprimento() == this.capacidade()) {
            expandirArray();
        }
        inicio = (inicio - 1 + area.length) % area.length;
        this.area[inicio] = algo;
        len++;
    }

    private void expandirArray() {
        T[] arr = (T[]) (new Object[this.area.length*2]);
        for (int i = 0; i < this.area.length; i++) {
            arr[i] = area[(this.inicio+i) % area.length];
        }
        this.fim = this.area.length;
        this.area = arr;
        this.inicio = 0;
    }

    public T extrai_inicio() {
        if (len == 0) throw new IndexOutOfBoundsException();
        len--;
        var obj = this.area[this.inicio];
        this.area[this.inicio] = null;
        inicio = (inicio+1) % area.length;
        return obj;
    }

    public T extrai_final(){
        if (len == 0) throw new IndexOutOfBoundsException("Fila vazia");

        fim = (fim - 1 + area.length) % area.length;

        var obj = this.area[fim];
        this.area[fim] = null;

        len--;
        return obj;
    }



    public T acessa_final() {
        if (len == 0) throw new IndexOutOfBoundsException("Fila vazia");
        return this.area[(fim - 1 + area.length) % area.length];
    }
    public T acessa_inicio(){
        if (len == 0) throw new IndexOutOfBoundsException("Fila vazia");
        return this.area[this.inicio];
    }

    public T acessa(int indice ){
        if (len == 0) throw new IndexOutOfBoundsException("Fila vazia");
        if (indice < 0 || indice >= len) throw new IndexOutOfBoundsException("Indice invalido");
        return this.area[this.inicio + indice];
    }

    public boolean esta_vazia() {
        if (this.comprimento() == 0) return true;
        return false;
    }

    public void limpa() {
        this.area = ((T[]) new Object[this.capacidade()]);
    }

    @Override
    public String toString() {
        return "Deque{" +
                "area=" + Arrays.toString(area) +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", len=" + len +
                '}';
    }
}
