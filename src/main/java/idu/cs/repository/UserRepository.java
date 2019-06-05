package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idu.cs.entity.UserEntity;

public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {
	//findById, save, delete 선언없이도 구현 가능
	
	//아래 메소드들은 선언해야 JPA규칙에 의해 구현됨
	//find=SELECT by=WHERE OrderBy=ORDER BY, ASC와 DESC 사용가능
	List<UserEntity> findByName(String name);
	List<UserEntity> findByCompany(String company);
	
	UserEntity findByUserId(String userId);
}
