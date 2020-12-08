package pl.decerto.recruit.dk.business.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.decerto.recruit.dk.business.api.DataProvider;

import java.math.BigDecimal;

@Component
@Qualifier("active")
class JavaRandomProvider implements DataProvider<BigDecimal> {

    @Override
    public BigDecimal generate() {
        return BigDecimal.valueOf(Math.random());
    }
}
