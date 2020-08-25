package com.healthinsurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.healthinsurance.model.User;
import com.healthinsurance.service.RegisterUserService;

@Controller
public class RegisterController {

	@Autowired
	private RegisterUserService service;

	@GetMapping("/register")
	public String loadRegistrationForm(Model model) {
		User user = new User();
		List<String> roles = RegisterController.addRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		model.addAttribute("userStatus");
		return "registration";

	}

	@PostMapping("/registerUser")
	public String handleSubmitBtn(@ModelAttribute("user") User user, RedirectAttributes redirect, Model model) {
		String statusOfUser = service.registerUser(user);
		redirect.addFlashAttribute("userStatus", statusOfUser);
		return "redirect:/register";
	}

	public static List<String> addRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("case Worker");
		return roles;
	}

	@GetMapping("/users")
	public String getAllUsers(Model model) {

		List<User> allUsersBasedOnRole = service.getAllUsers();
		model.addAttribute("users", allUsersBasedOnRole);
		List<String> roles = RegisterController.addRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("status");
		return "viewAccounts";

	}

	@GetMapping("/editAccount")
	public String editPlanBasedOnPlanId(@RequestParam("accountId") Integer userId, Model model) {
		User acctDtls = service.updateAccountDetailsBasedOnUserId(userId);
		model.addAttribute("user", acctDtls);
		model.addAttribute("roles", RegisterController.addRoles());
		return "registration";
	}

	@GetMapping("/deleteAccount")
	public String deleteBasedOnAccountId(@RequestParam("accountId") Integer planId, RedirectAttributes redirect,
			Model model) {
		User planStatus = service.checkAccountActivationSwitchToDeleteOrActivate(planId);
		if (planStatus.getAccountSwitch().equalsIgnoreCase("N")) {
			redirect.addFlashAttribute("status", "account activated sucessfully");
		} else {
			redirect.addFlashAttribute("status", "account deletes sucessfully");

		}
		return "redirect:/users";

	}

}
