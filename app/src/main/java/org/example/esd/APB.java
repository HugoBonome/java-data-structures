package esd;

import java.util.ArrayList;
import java.util.List;

public class APB<T extends Comparable<T>> {
    NodoAPB<T> raiz = null;
    int len = 0;
    class NodoAPB<T extends Comparable<T>> {
        T valor;
        NodoAPB esq = null;
        NodoAPB dir = null;
        NodoAPB pai = null;
        NodoAPB(T val){
            valor = val;
        }
    }

    public void adiciona(T val){
        if (len == 0){
            raiz = new NodoAPB<>(val);
            len = 1;
        } else {
            NodoAPB nodo = raiz;
            while (true){
                int cmp = val.compareTo((T) nodo.valor);
                if (cmp == 0){
                    nodo.valor = val;
                    break;
                }
                if (cmp < 0){
                    if (nodo.esq != null) {
                        nodo = nodo.esq;
                    } else {
                        nodo.esq = new NodoAPB<>(val);
                        len++;
                        break;
                    }
                } else {
                    if (nodo.dir != null) {
                        nodo = nodo.dir;
                    } else {
                        nodo.dir = new NodoAPB<>(val);
                        len++;
                        break;
                    }
                }
            }
        }
    }

    public T procura(T val){
        if (raiz == null){
            return null;
        } else {
            NodoAPB nodo = raiz;
            while (true){

                int cmp = val.compareTo((T) nodo.valor);

                if (cmp == 0){
                    return (T) nodo.valor;
                }
                if (cmp < 0){
                    if (nodo.esq != null) {
                        nodo = nodo.esq;
                    } else {
                        return null;
                    }
                } else {
                    if (nodo.dir != null) {
                        nodo = nodo.dir;
                    } else {
                        return null;
                    }

                }
            }
        }
    }

    public ListaSequencial emOrdem(){
        ListaSequencial<T> result = new ListaSequencial<>();
        _emOrdem(raiz, result);
        return result;
    }
    private void _emOrdem(NodoAPB nodo, ListaSequencial resp){
        if (nodo == null) return;
        _emOrdem(nodo.esq, resp);
        resp.adiciona(nodo.valor);
        _emOrdem(nodo.dir, resp);
    }

    public ListaSequencial preOrdem(){
        ListaSequencial<T> result = new ListaSequencial<>();
        _preOrdem(raiz, result);
        return result;
    }
    private void _preOrdem(NodoAPB nodo, ListaSequencial resp){
        if (nodo == null) return;
        resp.adiciona(nodo.valor);
        _preOrdem(nodo.esq, resp);
        _preOrdem(nodo.dir, resp);
    }

    public ListaSequencial posOrdem(){
        ListaSequencial<T> result = new ListaSequencial<>();
        _posOrdem(raiz, result);
        return result;
    }

    private void _posOrdem(NodoAPB nodo, ListaSequencial resp){
        if (nodo == null) return;
        _posOrdem(nodo.esq, resp);
        _posOrdem(nodo.dir, resp);
        resp.adiciona(nodo.valor);
    }

    public ListaSequencial emLargura(){
        if (raiz == null) {
            throw new IndexOutOfBoundsException("Arvore vazia.");
        }
        ListaSequencial<T> result = new ListaSequencial<>();
        Fila<NodoAPB> fila = new Fila<>();
        fila.adiciona(raiz);

        while (!fila.estaVazia()) {
            NodoAPB nodo = fila.remove();
            result.adiciona((T) nodo.valor);
            if (nodo.esq != null) {
                fila.adiciona(nodo.esq);
            }
            if (nodo.dir != null) {
                fila.adiciona(nodo.dir);
            }
        }
        return result;
    }

    public int altura(){
        if (raiz == null){
            return 0;
        }
        return calcula_altura(raiz);
    }
    private int calcula_altura(NodoAPB nodo){
        int ae = 0, ad = 0;
        if (nodo.esq != null) ae = 1 + calcula_altura(nodo.esq);
        if (nodo.dir != null) ad = 1 + calcula_altura(nodo.dir);
        return Math.max(ae, ad);
    }

    private int calculaFb(NodoAPB nodo){
        if (nodo == null) { return 0;}
        int ae = 0, ad = 0;
        if (nodo.esq != null) ae = 1 + calcula_altura(nodo.esq);
        if (nodo.dir != null) ad = 1 + calcula_altura(nodo.dir);
        return ae - ad;
    }

    private NodoAPB rotacionaDireita(NodoAPB nodo) {
        NodoAPB novaRaiz = nodo.esq;
        NodoAPB b = novaRaiz.dir;
        novaRaiz.dir = nodo;
        nodo.esq = b;
        return novaRaiz;
    }

    private NodoAPB rotacionaEsquerda(NodoAPB nodo) {
        NodoAPB novaRaiz = nodo.dir;
        NodoAPB b = novaRaiz.esq;
        novaRaiz.esq = nodo;
        nodo.dir = b;
        return novaRaiz;
    }

    private NodoAPB reduzEsquerda(NodoAPB raiz) {
        NodoAPB<T> nodo = raiz;
        if (calculaFb(nodo.esq) < 0) {
            nodo.esq = rotacionaDireita(nodo.esq);
        }
        nodo = rotacionaEsquerda(nodo);
        return nodo;
    }

    private NodoAPB reduzDireita(NodoAPB raiz) {
        NodoAPB<T> nodo = raiz;
        if (calculaFb(nodo.dir) > 0) {
            nodo.dir = rotacionaDireita(nodo.dir);
        }
        nodo = rotacionaEsquerda(nodo);
        return nodo;
    }
    public void balanceia(){
        raiz = balanceiaPorRaiz(raiz);
    }

    private NodoAPB balanceiaPorRaiz(NodoAPB raiz) {

        if (raiz == null) {
            return null;
        }

        if (raiz.esq != null) raiz.esq = balanceiaPorRaiz(raiz.esq);
        if (raiz.dir != null) raiz.dir = balanceiaPorRaiz(raiz.dir);

        while (calculaFb(raiz) < -1) {
            raiz = reduzDireita(raiz);
        }

        while (calculaFb(raiz) > 1) {
            raiz = reduzEsquerda(raiz);
        }

        return raiz;
    }

    public boolean esta_vazia(){
        return len == 0;
    }

    public int tamanho(){
        return len;
    }


    public T menor(){
        if (raiz == null){
            return null;
        } else {
            NodoAPB nodo = raiz;
            while (true){
                if (nodo.esq != null) {
                    nodo = nodo.esq;
                } else {
                    return (T) nodo.valor;
                }
            }
        }
    }
    public T  maior(){
        if (raiz == null){
            return null;
        } else {
            NodoAPB nodo = raiz;
            while (true){
                if (nodo.dir != null) {
                    nodo = nodo.dir;
                } else {
                    return (T) nodo.valor;
                }
            }
        }
    }

    private NodoAPB<T> menorPorNodo(NodoAPB nodo){
        while (true){
            if (nodo.esq != null) {
                nodo = nodo.esq;
            } else {
                return nodo;
            }
        }

    }
    private NodoAPB<T> maiorPorNodo(NodoAPB nodo){
        while (true){
            if (nodo.dir != null) {
                nodo = nodo.dir;
            } else {
                return nodo;
            }
        }
    }
    public T menor_que(T obj) {
        NodoAPB nodo = raiz;
        T candidato = null;
        while (nodo != null) {
            int cmp = obj.compareTo((T) nodo.valor);
            if (cmp >= 0) {
                candidato = (T) nodo.valor;
                nodo = nodo.dir;
            } else {
                nodo = nodo.esq;
            }
        }
        return candidato;
    }

    public T maior_que(T obj) {
        NodoAPB nodo = raiz;
        T candidato = null;
        while (nodo != null) {
            int cmp = obj.compareTo((T) nodo.valor);
            if (cmp <= 0) {
                candidato = (T) nodo.valor;
                nodo = nodo.esq;
            } else {
                nodo = nodo.dir;
            }
        }
        return candidato;
    }
    public ListaSequencial maiores_que(T obj){
        ListaSequencial<T> resp = new ListaSequencial<>();
        _maiores_que(raiz, obj, resp);
        return resp;
    }

    private void _maiores_que(NodoAPB nodo, T obj, ListaSequencial resp){
        if (nodo == null) return;
        if (nodo.valor.compareTo(obj) > 0) {
            resp.adiciona(nodo.valor);
        }
        _maiores_que(nodo.esq, obj, resp);
        _maiores_que(nodo.dir, obj, resp);
    }

    public ListaSequencial<T> menores_que(T valor) {
        ListaSequencial<T> resp = new ListaSequencial<>();
        _menores_que(raiz, valor,resp);
        return resp;
    }

    private void _menores_que(NodoAPB nodo, T valor, ListaSequencial<T> resp) {
        if (nodo == null) return;

        _menores_que(nodo.esq, valor, resp);

        if (nodo.valor.compareTo(valor) <= 0) {
            resp.adiciona( (T) nodo.valor);
        }

        _menores_que(nodo.dir, valor, resp);
    }

    public ListaSequencial<T> faixa(T inicio, T fim) {
        ListaSequencial<T> resp = new ListaSequencial<>();
        _faixa(raiz, inicio, fim, resp);
        return resp;
    }

    private void _faixa(NodoAPB nodo, T inicio, T fim, ListaSequencial<T> resp) {
        if (nodo == null) return;

        _faixa(nodo.esq, inicio, fim, resp);

        if (nodo.valor.compareTo(inicio) >= 0 && nodo.valor.compareTo(fim) <= 0) {
            resp.adiciona( (T) nodo.valor);
        }

        _faixa(nodo.dir, inicio, fim,resp);
    }



    public T remove(T val){
        if (raiz == null){
            return null;
        }
        NodoAPB nodo = raiz;
        while (true){

            int cmp = val.compareTo((T) nodo.valor);

            if (cmp == 0){
                if(nodo.esq != null){
                    nodo = maiorPorNodo(nodo.esq);
                } else {
                    nodo = menorPorNodo(nodo.dir);
                }
                return (T) nodo.valor;
            }
            if (cmp < 0){
                if (nodo.esq != null) {
                    nodo = nodo.esq;
                }
            } else {
                if (nodo.dir != null) {
                    nodo = nodo.dir;
                }

            }
        }
    }

}

