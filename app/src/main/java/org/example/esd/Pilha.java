package esd;

public class Pilha <T> {

    T[] mem; // a área de armazenamento
    int len; // a quantidade de dados armazenados
    final int defcap = 8; // capacidade inicial

    @SuppressWarnings("unchecked")
    public Pilha() {
        mem = (T[])new Object[defcap];
        len = 0;
    }

    public int comprimento() {
        return len;
    }

    public int capacidade() {
        return mem.length;
    }

    public void empilha(T algo) {
        if (len == capacidade()) {aumentaCapacidade();}
        mem[len] = algo;
        len++;
    }

    public T desempilha() {
        if (!estaVazia()) {
            T resp = mem[len-1];
            mem[len-1] = null;
            len--;
            return resp;
        } else {
            throw new IndexOutOfBoundsException("Pilha vazia");
        }
    }

    public T topo() {
        return mem[len-1];
    }

    public boolean estaVazia() {
        return len == 0;
    }

    public void limpa(){
        len = 0;
    }

    @SuppressWarnings("unchecked")
    public void aumentaCapacidade() {
        T[] arr = (T[]) (new Object[mem.length*2]);
        for (int i = 0; i < mem.length; i++) {
            arr[i] = mem[i];
        }
        mem = arr;
    }
}
