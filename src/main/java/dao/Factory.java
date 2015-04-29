package dao;

/**
 * Created by Administrator on 18.03.2015.
 */
public class Factory {
    private static Factory instance = null;
    private DaoEntity dao;

    public static Factory getInstance(){
        if(instance == null) instance = new Factory();
        return instance;
    }

    public<T> DaoEntity<T> getDao(){
        return new DaoEntityImplementation<T>();
    }
}
