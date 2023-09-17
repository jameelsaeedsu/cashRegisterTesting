package se.su.dsv.grupp03;

import java.math.BigDecimal;

public enum EnumVat {

        SIX_PERCENT(new BigDecimal("0.06")), TWELVE_PERCENT(new BigDecimal("0.12")), TWENTY_FIVE_PERCENT(new BigDecimal("0.25"));

        private final BigDecimal taxPercentage;

        EnumVat(BigDecimal Vat){
            this.taxPercentage = Vat;
        }

    public BigDecimal getInBigDecimal() {
        return taxPercentage;
    }

}
