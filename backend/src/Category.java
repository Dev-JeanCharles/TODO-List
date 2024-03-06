public enum Category {

    VIDEO("Videos"),

    DOCUMENTACAO("Documentação");

    private String valor;

    Category(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
