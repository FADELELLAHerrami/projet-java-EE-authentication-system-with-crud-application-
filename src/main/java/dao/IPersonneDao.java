package dao;

public interface IPersonneDao<T> {
	void save(T obj);
	boolean findByEmailAndPassword(String email,String password);
}
