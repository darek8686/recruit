package pl.decerto.recruit.dk.business.api;

import java.util.List;

public interface MergeOperation<T> {

    T execute(List<T> data);

}
