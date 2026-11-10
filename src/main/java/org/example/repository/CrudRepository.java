package org.example.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;
    Optional<T> findById(Long id);
    void save(T entity) throws SQLException;
    void update(T entity);
    void remove(T entity) throws SQLException;
    void removeById(Long id);

    void removeAll() throws SQLException;
    void loadToDatabase(T[] entities) throws SQLException;
}
