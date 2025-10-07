package esd;


public class ListaSimples <T> {
    class Node{
        T valor;
        Node proximo;

        public Node(T valor) {
            this.valor = valor;
        }

    }
    Node primeiro = null;
    Node ultimo = null;
    int len = 0;



    public ListaSimples() {




    }

    public void open(){};

    // adiciona no fim
    public void adiciona(T valor){
        Node nodo = new Node(valor);
        if (primeiro != null){
            ultimo.proximo = nodo; // o atual ultimo vai apontar para o nodo adicionado
        } else {
            primeiro = nodo;
        }
        ultimo = nodo;// o nodo adicionado vira o último
        len++;
    }

    public void insere(int indice, T valor){
        if (indice > len){
            throw new IndexOutOfBoundsException("Indice invalido");
        }
        Node nodo = primeiro;
        Node nodoInserir = new Node(valor);

        if (indice != 0) {
            indice--;
            while (indice-- > 0) {
                nodo = nodo.proximo;
            }
            nodoInserir.proximo = nodo.proximo;
            nodo.proximo = nodoInserir;
        } else {
            nodoInserir.proximo = nodo;
            primeiro = nodoInserir;
        }
        len++;

    }

    public void remove(int indice){
        if (esta_vazia()){
            throw new IndexOutOfBoundsException("Lista vazia");
        }
        Node nodo = primeiro;
        if (indice != 0) {
            indice--;
            while (indice-- > 0) {
                nodo = nodo.proximo;
            }
            Node nodoRemover = nodo.proximo;
            nodo.proximo = nodoRemover.proximo;
        } else {
            primeiro = primeiro.proximo;
        }
        len--;
    }
    public void remove_ultimo(){remove(len-1);}


    // Obtém valor que está na osição dad apor indice
    // se índice >= comprimento da lista, dispara exceção
    public T obtem(int indice){
        if (indice >= len){
            throw new IndexOutOfBoundsException("Indice invalido");
        }
        Node nodo = primeiro;
        while (indice-- > 0){
            nodo = nodo.proximo;
        }
        return nodo.valor;
    }

    public T obtem_primeiro(){return primeiro.valor;}
    public T obtem_ultimo(){return ultimo.valor;}
    public int procura(T obj){
        //TODO
        return 0;}

    public void substitui(int indice, T obj){
        remove(indice);
        insere(indice, obj);
    }
    public int comprimento(){return len;}
    public void limpa(){}
    public void ordena(){}
    public void inverte(){
        if (esta_vazia()){
            throw new IndexOutOfBoundsException("Lista vazia");
        }
        
    }
    public void embaralha(){}
    public boolean esta_vazia(){return len == 0;}

}
