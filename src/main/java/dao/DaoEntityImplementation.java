package dao;


import javafx.scene.control.Alert;
import org.hibernate.Session;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 18.03.2015.
 */
public class DaoEntityImplementation<T> implements DaoEntity<T>{
    private Session session = null;
    private List<T> lst = null;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public List<T> getAll(Class<?> type) {
        try {
            session = HibernateUtil.getInstance().openSession();
            session.beginTransaction();
            lst = session.createCriteria(type).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Problem with getAll");
            e.printStackTrace();
        } finally {
            if (session.isOpen() && session != null) session.close();
            return lst;
        }
    }
    @Override
    public List<T> getAllByArguments(String select, String from, String where, int value, Class<?> type) {
        try{
            session = HibernateUtil.getInstance().openSession();
            session.beginTransaction();

            /*
            * This query returns correct data but in controller i have object to entity cast exception
            * lst = session.createSQLQuery("SELECT *" + " FROM " + from + " WHERE " + where + " = " + value).list();
            * */
            lst = session.createCriteria(type).add(org.hibernate.criterion.Expression.eq("" + where, value)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem with getByArguments");
        } finally {
            if(session.isOpen() && session != null) session.close();
            return lst;
        }

    }
    @Override
    public void update(T instance) {
       try{
           session = HibernateUtil.getInstance().openSession();
           session.beginTransaction();
           session.update(instance);
           session.getTransaction().commit();
           alert.setContentText("Data saved!");
           alert.setHeaderText("");
           alert.showAndWait();
       }catch (Exception e){
           System.out.println("Problem with update");
           e.printStackTrace();
       }finally {
           if (session.isOpen() && session != null) session.close();
       }
    }
    @Override
    public void add(T instance) {
        try{
            session = HibernateUtil.getInstance().openSession();
            session.beginTransaction();
            session.save(instance);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println("problem with add");
            e.printStackTrace();
        } finally {
            if(session.isOpen() && session != null) session.close();
        }
    }
    @Override
    public void delete(T instance) {
        try{
            session = HibernateUtil.getInstance().openSession();
            session.beginTransaction();
            session.delete(instance);
            session.getTransaction().commit();
            alert.setContentText("Row deleted!");
            alert.setHeaderText("");
        }catch (Exception e){
            System.out.println("Problem with delete");
            e.printStackTrace();
        }finally {
            if (session.isOpen() && session != null) session.close();
        }


    }

}
