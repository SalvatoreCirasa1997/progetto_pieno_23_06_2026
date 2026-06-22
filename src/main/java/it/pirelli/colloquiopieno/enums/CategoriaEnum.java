package it.pirelli.colloquiopieno.enums;

import lombok.Getter;

@Getter
public enum CategoriaEnum {
    ABBIGLIAMENTO(0),
    ELETTRONICA(1),
    ALIMENTI(2);

    private final Integer valore;

    private CategoriaEnum(Integer valore) {
        this.valore = valore;
    }

    public Integer getValore() {
        return valore;
    }
}
