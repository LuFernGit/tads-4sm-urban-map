package br.com.senac.urbanmap.entities.usuario;

public enum Funcao {
    USUARIO("usuario"), ADMINISTRADOR("administrador");

    String nivel;

    Funcao(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }
}
