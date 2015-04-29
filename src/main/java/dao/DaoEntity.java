package dao;

import java.util.List;

/**
 * Created by Administrator on 18.03.2015.
 */
public interface DaoEntity<T> {
    List<T> getAll(Class<?> type);
    List<T> getAllByArguments(String select, String from, String where, int value, Class<?> type);
    void update(T instance);
    void add(T instance);
    void delete(T instance);

}
