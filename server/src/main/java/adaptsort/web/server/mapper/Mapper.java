package adaptsort.web.server.mapper;

public interface Mapper<S, T> {
    T mapToDto(S object);
}
