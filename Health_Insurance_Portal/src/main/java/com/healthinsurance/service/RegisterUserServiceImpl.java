package com.healthinsurance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthinsurance.applicationconstants.ApplicationConstants;
import com.healthinsurance.entities.RegisterUserEntity;
import com.healthinsurance.entities.RegisterUserRepo;
import com.healthinsurance.model.UnlockAccount;
import com.healthinsurance.model.User;
import com.healthinsurance.utilities.EmailUtils;
import com.healthinsurance.utilities.PasswordGenerator;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
	@Autowired
	private RegisterUserRepo repo;
	@Autowired
	private EmailUtils emailUtils;

	public String registerUser(User user) {
		RegisterUserEntity register = null;
		RegisterUserEntity entity = repo.findByEmail(user.getEmail());
		if (user.getId() != null) {
			register = new RegisterUserEntity();
			BeanUtils.copyProperties(user, register);
			repo.save(register);
			return "user updated sucessfully";
		} else if (entity != null) {
			return "user already registered with mail-id please check";
		} else {
			register = new RegisterUserEntity();
			BeanUtils.copyProperties(user, register);
			register.setLockStatus(ApplicationConstants.LOCK_STATUS);
			register.setPazzword(PasswordGenerator.generateTemporaryPazzword());
			register.setAccountAction(ApplicationConstants.ACCOUNT_ACTION);
			register.setAccountSwitch("N");
			RegisterUserEntity savedEntity = repo.save(register);
			emailUtils.sendUserAccUnlockEmail(savedEntity);
			return "user registered sucessfully please unlock account your email";

		}

	}

	@Override
	public User unlockAccount(UnlockAccount acc) {
		User reg = new User();
		String email = acc.getEmail();
		String tempPwd = acc.getTemporaryPazzword();
		RegisterUserEntity entity = repo.findByEmail(email);
		if (entity != null) {
			if (entity.getPazzword().equals(tempPwd)) {
				entity.setLockStatus("unlocked");
				entity.setPazzword(acc.getNewPazzword());
				RegisterUserEntity updatedEntity = repo.save(entity);
				BeanUtils.copyProperties(updatedEntity, reg);
				return reg;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<RegisterUserEntity> allWorkers = repo.findAll();
		List<User> allUsers = allWorkers.stream().map(entity -> {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			return user;
		}).collect(Collectors.toList());
		return allUsers;
	}

	@Override
	public User updateAccountDetailsBasedOnUserId(Integer userId) {
		RegisterUserEntity entity = repo.findById(userId);
		User reg = new User();
		BeanUtils.copyProperties(entity, reg);
		return reg;
	}

	@Override
	public User checkAccountActivationSwitchToDeleteOrActivate(Integer userId) {
		User planDetails = new User();
		RegisterUserEntity entity = repo.findById(userId);
		if (entity.getAccountSwitch().equalsIgnoreCase("N")) {
			entity.setAccountSwitch("Y");
		} else {
			entity.setAccountSwitch("N");
		}
		RegisterUserEntity savedEntity = repo.save(entity);
		BeanUtils.copyProperties(savedEntity, planDetails);
		return planDetails;
	}

}
