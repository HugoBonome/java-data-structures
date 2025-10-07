package esd;

public class Fila<T> {
    T[] area;
    int inicio = 0, fim = 0;
    int len = 0;

    @SuppressWarnings("unchecked") // relaxa, dog
    public Fila() {
        this.area = ((T[]) new Object[2]);
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
        fim = (fim+1) % area.length;
    }

    @SuppressWarnings("unchecked")
    private void expandirArray() {
        T[] arr = (T[]) (new Object[this.area.length*2]);
        for (int i = 0; i < this.area.length; i++) {
            arr[i] = area[(this.inicio+i) % area.length];
        }
        this.fim = this.area.length;
        this.area = arr;
        this.inicio = 0;
    }

    public T remove() {
        if (len == 0) throw new IndexOutOfBoundsException();
        len--;
        var obj = this.area[this.inicio];
        inicio = (inicio+1) % area.length;
        return obj;
    }

    public T frente() {
        if (len == 0) throw new IndexOutOfBoundsException();
        return this.area[this.inicio];
    }

    public boolean estaVazia() {
        if (this.comprimento() == 0) return true;
        return false;
    }

    public T tras() {
        if (len == 0) throw new IndexOutOfBoundsException();
        return this.area[this.fim];
    }

    @SuppressWarnings("unchecked")
    public void limpa() {
        this.area = ((T[]) new Object[this.capacidade()]);
    }
}
