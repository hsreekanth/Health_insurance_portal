package com.healthinsurance.service;

import java.util.List;

import com.healthinsurance.model.UnlockAccount;
import com.healthinsurance.model.User;

public interface RegisterUserService {
	public String registerUser(User user);

	public User unlockAccount(UnlockAccount account);

	public User updateAccountDetailsBasedOnUserId(Integer userID);

	public List<User> getAllUsers();

	public User checkAccountActivationSwitchToDeleteOrActivate(Integer userId);

}
