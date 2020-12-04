package pl.decerto.recruit.dk.business.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.decerto.recruit.dk.business.api.ComputeService;
import pl.decerto.recruit.dk.business.api.DataMerger;
import pl.decerto.recruit.dk.business.api.DataProvider;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
class ComputeServiceImpl implements ComputeService<BigDecimal> {

    private final DataProvider<BigDecimal> firstProvider;
    private final DataProvider<BigDecimal> secondProvider;
    private final DataMerger<BigDecimal> dataMerger;

    public ComputeServiceImpl(@Qualifier("restRandom") DataProvider<BigDecimal> firstProvider,
                              DataProvider<BigDecimal> secondProvider, DataMerger<BigDecimal> dataMerger) {
        this.firstProvider = firstProvider;
        this.secondProvider = secondProvider;
        this.dataMerger = dataMerger;
    }

    @Override
    public BigDecimal compute() {
        final BigDecimal first = firstProvider.generate();
        final BigDecimal second = secondProvider.generate();

        return dataMerger.merge(Arrays.asList(first, second));
    }
}
