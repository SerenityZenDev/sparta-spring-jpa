package org.example.my;

import java.util.List;

public interface MyRepository<T> {


    void delete(T entity);

    List<String> findNameAll();

}
