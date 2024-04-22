package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;

public class HoaDonDao {
	private final SessionFactory sessionFactory;

    public HoaDonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
