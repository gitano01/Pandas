package mx.com.efectivale.efectivacontigo.autorizacion.repository;

import org.springframework.data.repository.CrudRepository;

import mx.com.efectivale.efectivacontigo.autorizacion.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String username);
}
