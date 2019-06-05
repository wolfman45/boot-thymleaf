package idu.cs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idu.cs.domain.User;
import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired UserRepository repository;
	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		UserEntity entity=null;
		try {
			entity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found id :: " + id));
		}catch(ResourceNotFoundException e) {
			e.printStackTrace();
		}
		User user=entity.buildDomain();
		return user;
	}

	@Override
	public User getUserByUserId(String userId) {
		// DB, repository에서 가져와서 Entity에 저장
		//UserRepository에 findByUserId
		UserEntity entity=repository.findByUserId(userId);
		User user=entity.buildDomain();
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		List<UserEntity> entities = repository.findAll();
		for(UserEntity entity : entities) {
			User user = entity.buildDomain();
			users.add(user);
		}
		return users;
	}

	@Override
	public List<User> getUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByCompany(String company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByPage(int index, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User User) {
		// TODO Auto-generated method stub
		UserEntity entity = new UserEntity();
		/*
		 * domain-user 객체를 entity-userEntity 생성
		 * DB저장을 위해 Repository가 entity를 필요로 함
		 */
		entity.buildEntity(User);
		repository.save(entity);
	}

	@Override
	public void updateUser(User User) {
		// TODO Auto-generated method stub
		UserEntity entity=new UserEntity();
		entity.buildEntity(User);
		repository.save(entity);
	}

	@Override
	public void deleteUser(User User) {
		// TODO Auto-generated method stub

	}

}
