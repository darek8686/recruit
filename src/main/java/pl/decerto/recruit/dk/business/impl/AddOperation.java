package pl.decerto.recruit.dk.business.impl;

import org.springframework.stereotype.Component;
import pl.decerto.recruit.dk.business.api.MergeOperation;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AddOperation implements MergeOperation<BigDecimal> {

    @Override
    public BigDecimal execute(List<BigDecimal> data) {
        return data.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
