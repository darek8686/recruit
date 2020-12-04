package pl.decerto.recruit.dk.business.impl;

import org.springframework.stereotype.Component;
import pl.decerto.recruit.dk.business.api.DataMerger;

import java.math.BigDecimal;
import java.util.List;

@Component
class BigDecimalMerger implements DataMerger<BigDecimal> {
    @Override
    public BigDecimal merge(List<BigDecimal> numbers) {
        if (numbers == null)
            throw new IllegalArgumentException();

        return numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
