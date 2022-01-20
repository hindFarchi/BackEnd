package session;

import java.util.List;
import java.util.Map;

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
import entities.User;

@Stateless
public class UserService implements IDaoLocal<User>, IDaoRemote<User> {
	
	@PersistenceContext
	private EntityManager entityManager;


	public UserService() {
		super();
	}

	@Override
	public boolean create(User u) {
		// TODO Auto-generated method stub
		entityManager.persist(u);
		return true;
	}

	@Override
	public boolean delete(User u) {
		// TODO Auto-generated method stub
		//em.remove(u);
		entityManager.remove(entityManager.contains(u)?u: entityManager.merge(u));
		return true;
	}
	
	@Override
	public boolean update(User u) {
		// TODO Auto-generated method stub
		entityManager.persist(entityManager.contains(u)?u: entityManager.merge(u));
		return true;
	}

	@Override
	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getAll() {
		Query query= entityManager.createQuery("from User");
		return query.getResultList();
	}

}
