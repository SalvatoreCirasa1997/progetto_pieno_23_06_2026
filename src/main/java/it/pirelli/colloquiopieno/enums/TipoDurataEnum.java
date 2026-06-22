package it.pirelli.colloquiopieno.enums;

import lombok.Getter;

@Getter
public enum TipoDurataEnum {
    ORE(0),
    GIORNI(1),
    MESI(2),
    ANNI(3);

    private Integer value;

    TipoDurataEnum(Integer value) {
        this.value = value;
    }

}
