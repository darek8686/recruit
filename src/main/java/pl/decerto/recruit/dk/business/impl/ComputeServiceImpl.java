package pl.decerto.recruit.dk.business.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.decerto.recruit.dk.business.api.ComputeService;
import pl.decerto.recruit.dk.business.api.DataMerger;
import pl.decerto.recruit.dk.business.api.DataProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
class ComputeServiceImpl implements ComputeService<BigDecimal> {

    private final List<DataProvider<BigDecimal>> providers;
    private final DataMerger<BigDecimal> dataMerger;

    public ComputeServiceImpl(@Qualifier("active") List<DataProvider<BigDecimal>> providers, DataMerger<BigDecimal> dataMerger) {
        this.providers = providers;
        this.dataMerger = dataMerger;
    }

    @Override
    public BigDecimal compute() {
        final List<BigDecimal> numbers = new ArrayList<>(providers.size());
        providers.forEach(p -> numbers.add(p.generate()));

        return dataMerger.merge(numbers);
    }
}
