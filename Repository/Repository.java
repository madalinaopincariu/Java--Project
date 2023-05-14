package Repository;

import Domain.Entity;
import java.util.Optional;

public interface Repository<ID, E extends Entity<ID>>{
    Iterable<E> getAll();
    Optional<E> add(E new_entity);
    Optional<E> delete(E entity);
}