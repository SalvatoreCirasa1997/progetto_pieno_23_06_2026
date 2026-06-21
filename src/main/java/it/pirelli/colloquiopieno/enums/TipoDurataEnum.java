package it.pirelli.colloquiopieno.enums;

import lombok.Getter;

@Getter
public enum TipoDurataEnum {
    GIORNI(0),
    MESI(1),
    ANNI(2);

    private Integer value;

    TipoDurataEnum(Integer value) {
        this.value = value;
    }

}
