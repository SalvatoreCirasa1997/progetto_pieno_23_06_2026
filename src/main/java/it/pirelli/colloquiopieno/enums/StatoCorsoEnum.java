package it.pirelli.colloquiopieno.enums;

public enum StatoCorsoEnum {
    NON_ANCORA_INIZIATO(0),
    IN_CORSO(1),
    COMPLETATO(2);

    private Integer value;

    StatoCorsoEnum(Integer value) {this.value = value;}

    public Integer getOrder() {
        return value;
    }
}
