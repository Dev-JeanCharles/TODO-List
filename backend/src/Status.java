public enum Status {

    TODO("Todo"),

    DOING("Doing"),

    DONE("Done");

    private String valor;

    Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
