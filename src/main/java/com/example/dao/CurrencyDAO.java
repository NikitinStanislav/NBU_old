package com.example.dao;

import com.example.domain.CurrencyJava;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CurrencyDAO {
/*

    @Autowired
    private EntityManager entityManager;

    public List<CurrencyJava> getAllCurrency(){
       // Session currentSession = entityManager.unwrap(Session.class);
        Session currentSession = HibernateUtil.getSessionFactory().openSession();
        Session c = entityManager.unwrap(Session.class);
        Query<CurrencyJava> query =  currentSession.createQuery("from CurrencyJava", CurrencyJava.class);
        return query.getResultList();
    }

    public void saveOrUpdate(CurrencyJava currencyJava){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(currencyJava);
    }

    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        CurrencyJava currencyJava = currentSession.get(CurrencyJava.class, id);
        currentSession.delete(currencyJava);
    }
*/

}
