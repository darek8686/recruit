package pl.decerto.recruit.dk.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.decerto.recruit.dk.business.api.DataMerger;
import pl.decerto.recruit.dk.business.api.MergeOperation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
class BigDecimalMerger implements DataMerger<BigDecimal> {

    private final List<MergeOperation<BigDecimal>> operations;

    @Override
    public BigDecimal merge(List<BigDecimal> numbers) {
        if (numbers == null || numbers.isEmpty())
            throw new IllegalArgumentException();

        List<BigDecimal> executeResults = new ArrayList<>(operations.size());
        operations.forEach(o -> executeResults.add(o.execute(numbers)));

        return executeResults.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
