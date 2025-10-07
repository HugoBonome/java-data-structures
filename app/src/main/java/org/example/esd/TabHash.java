package esd;

public class TabHash <K, V> {
    public class Par {
        private final K chave;
        private V valor;

        public Par(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public V obtemValor() {
            return valor;
        }

        public K obtemChave() {
            return chave;
        }
    }
    private ListaSequencial<ListaSequencial<Par>> tab = new ListaSequencial<>();
    private int len = 0;
    private int defcap = 16;

    public TabHash() {
        for (int i = 0; i < defcap; i++) {
            tab.adiciona(new ListaSequencial<>());
        }
    }
    public V obtem(K chave) {
        ListaSequencial<Par> linha = obtem_linha(chave);
        Par par = obtem_par(linha, chave);
        if (par != null) {
            return par.valor;
        }
        throw new IndexOutOfBoundsException("Chave não existe");
    }
    private ListaSequencial<Par> obtem_linha(K chave) {
        int hash = gerarHash(chave);
        return tab.obtem(hash);
    }
    private Par obtem_par(ListaSequencial<Par> linha, K chave) {
        for (int i = 0; i < linha.comprimento(); i++) {
            if (linha.obtem(i).chave.equals(chave)) {
                return linha.obtem(i);
            }
        }
        return null;
    }

    public void adiciona(K chave, V valor) {
        ListaSequencial<Par> linha = obtem_linha(chave);
        Par par = obtem_par(linha, chave);
        if (par != null) {
            par.valor = valor;
        } else {
            linha.adiciona(new Par(chave, valor));
            len++;
            if (len >= defcap) {
                expande();
            }
        }
    }


    public boolean contem(K chave) {
        ListaSequencial<Par> linha = obtem_linha(chave);
        Par par = obtem_par(linha, chave);
        return par != null;
    }
    public V obtem_ou_default(K chave, V defval) {
        ListaSequencial<Par> linha = obtem_linha(chave);
        Par par = obtem_par(linha, chave);
        if (par != null) {
            return par.valor;
        }
        return defval;
    }

    public void remove(K chave) {
        ListaSequencial<Par> linha = obtem_linha(chave);
        for (int j = 0; j < linha.comprimento(); j++) {
            if (linha.obtem(j).chave.equals(chave)) {
                linha.remove(j);
                len--;
                return;
            }
        }
    }
    public ListaSequencial<V> valores() {
        ListaSequencial<V> valores = new ListaSequencial<>();
        for (int i = 0; i < tab.comprimento(); i++) {
            for (int j = 0; j < tab.obtem(i).comprimento(); j++){
                valores.adiciona(tab.obtem(i).obtem(j).obtemValor());
            }
        }
        return valores;
    }

    public ListaSequencial<K> chaves() {
        ListaSequencial<K> chaves = new ListaSequencial<>();
        for (int i = 0; i < tab.comprimento(); i++) {
            for (int j = 0; j < tab.obtem(i).comprimento(); j++){
                chaves.adiciona(tab.obtem(i).obtem(j).obtemChave());
            }
        }
        return chaves;
    }
    public ListaSequencial<Par> items() {
        ListaSequencial<Par> todos_items = new ListaSequencial<>();
        for (int i = 0; i < tab.comprimento(); i++) {
            for (int j = 0; j < tab.obtem(i).comprimento(); j++){
                todos_items.adiciona(tab.obtem(i).obtem(j));
            }
        }
        return todos_items;
    }

    private int gerarHash(K chave) {
        return Math.abs(chave.hashCode()) % tab.comprimento();
    }
    public boolean esta_vazia() {
        return len == 0;
    }

    public int comprimento() { return len; }

    void expande() {
        // Salva a tabela atual
        ListaSequencial<ListaSequencial<Par>> tab_antiga = tab;
        int old_cap = defcap;

        defcap = defcap * 2;
        tab = new ListaSequencial<>();
        for (int i = 0; i < defcap; i++) {
            tab.adiciona(new ListaSequencial<>());
        }

        len = 0;
        for (int i = 0; i < old_cap; i++) {
            ListaSequencial<Par> linha = tab_antiga.obtem(i);
            for (int j = 0; j < linha.comprimento(); j++) {
                Par par = linha.obtem(j);
                ListaSequencial<Par> nova_linha = obtem_linha(par.obtemChave());
                nova_linha.adiciona(par);
                len++;
            }
        }
    }
}

