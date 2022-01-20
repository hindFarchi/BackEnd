package session;

import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import dao.IDaoLocal;
import dao.IDaoRemote;
import entities.Smartphone;
import entities.User;

@Stateless
public class SmartphoneService implements IDaoLocal<Smartphone>, IDaoRemote<Smartphone> {
	
	@PersistenceContext
	private EntityManager entityManager;

	public SmartphoneService() {
		super();
		
	}

	@Override
	public boolean create(Smartphone u) {
		entityManager.persist(u);
		return true;
	}

	@Override
	public boolean delete(Smartphone u) {
		entityManager.remove(entityManager.contains(u)?u: entityManager.merge(u));
		return true;
	}

	@Override
	public boolean update(Smartphone u) {
		entityManager.persist(entityManager.contains(u)?u: entityManager.merge(u));
		return true;
	}

	@Override
	public Smartphone getById(int id) {
		return entityManager.find(Smartphone.class, id);
	}

	@Override
	public List<Smartphone> getAll() {
		Query query= entityManager.createQuery("from Smartphone");
		return query.getResultList();
	}

}
