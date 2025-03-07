package adaptsort.web.server.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMapper<S, T> implements Mapper<S, T> {
    public List<T> mapToDto(Collection<S> object) {
        List<T> targets = new ArrayList<>();
        object.forEach(s -> targets.add(mapToDto(s)));
        return targets;
    }
}
