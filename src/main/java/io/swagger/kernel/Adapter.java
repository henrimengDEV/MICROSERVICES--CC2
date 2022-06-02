package io.swagger.kernel;

public interface Adapter<S, D> {

    D adapt(S source);
}
