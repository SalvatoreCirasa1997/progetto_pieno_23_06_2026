package it.pirelli.colloquiopieno.enums;

public enum StatoCorsoEnum {
    NON_ANCORA_INIZIATO("Non ancora iniziato"),
    IN_CORSO("In corso"),
    COMPLETATO("Completato");

    private String value;

    StatoCorsoEnum(String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
