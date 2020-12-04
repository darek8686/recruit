package pl.decerto.recruit.dk.business.api;

import java.util.List;

public interface DataMerger<T> {

    T merge(List<T> numbers);
}
