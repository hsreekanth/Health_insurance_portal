package com.healthinsurance.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RegisterUserRepo extends JpaRepository<RegisterUserEntity, Serializable> {
	
	public RegisterUserEntity findByEmail(String email);
	public RegisterUserEntity findById(Integer id);
	public List<RegisterUserEntity> findByRole(String role);

}
