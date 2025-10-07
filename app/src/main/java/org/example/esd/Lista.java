package esd;

import java.security.InvalidParameterException;

public class Lista <T> {

    class Node {
        T valor = null;
        Node proximo;
        Node antecessor;

        Node() {
            proximo = this;
            antecessor = this;
        }


        // operações de Node
        Node(T valor) {
            proximo =this;
            antecessor = this;
            this.valor = valor;
        }

        void conecta(Node sucessor) {
            this.proximo = sucessor;
            this.antecessor = sucessor.antecessor;
            this.antecessor.proximo = this;
            this.proximo.antecessor = this;

        }

        void desconecta() {
            this.antecessor.proximo = this.proximo;
            this.proximo.antecessor = this.antecessor;
        }
    }
    Node guarda;
    int len = 0;
    // operações de Lista
    public Lista() {
        guarda = new Node();
    }

    Node obtem_nodo(int indice) {
        Node nodo;

        if (indice < len / 2) {
            nodo=guarda.proximo;
            while (indice-- > 0) {
                nodo = nodo.proximo;
            }
        } else {
            nodo = guarda;
            indice = len - indice;
            while (indice-- > 0) {
                nodo = nodo.antecessor;
            }
        }
        return nodo;
    }

    // adiciona no fim
    public void adiciona(T valor) {
        Node nodo = new Node(valor);
        nodo.conecta(guarda);
        len++;
    }

    public void insere(int indice, T valor) {
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Indice invalido");

        }
        Node nodo = new Node(valor);
        Node sucessor = obtem_nodo(indice);
        nodo.conecta(sucessor);
        len++;
    }

    public void remove (int indice) {
        if (indice < 0 || indice > len) {
            throw new InvalidParameterException("Indice invalido");
        }
        Node nodo = obtem_nodo(indice);
        nodo.desconecta();
        len--;
    }
    public void remove_ultimo(){
        obtem_nodo(len-1).proximo.desconecta();
    }

    // Obtém valor que está na osição dad apor indice
    // se índice >= comprimento da lista, dispara exceção
    public T obtem(int indice){
        if (indice >= len){
            throw new IndexOutOfBoundsException("Indice invalido");
        }
        Node nodo = obtem_nodo(indice);
        return nodo.valor;
    }

    public T obtem_primeiro(){
        return guarda.antecessor.valor;
    }
    public T obtem_ultimo(){
        return guarda.proximo.valor;
    }
    public int procura(T obj){
        int indice = len=1;
        Node nodo = guarda.antecessor;
        while (indice-- > 0) {

        }
        do {
            nodo = nodo.proximo;
            indice--;
        } while (nodo.valor != obj);

        return indice;
    }

    public void substitui(int indice, T obj){
        remove(indice);
        insere(indice, obj);
    }
    public int comprimento(){return len;}
    public void limpa(){
        guarda = guarda;
    }
    public void ordena(){}
    public void inverte(){
        if (esta_vazia()){
            throw new IndexOutOfBoundsException("Lista vazia");
        }

    }
    public void embaralha(){}
    public boolean esta_vazia(){return len == 0;}
};