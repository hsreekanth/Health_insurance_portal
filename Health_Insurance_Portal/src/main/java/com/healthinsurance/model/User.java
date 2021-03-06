package com.healthinsurance.model;

import lombok.Data;

@Data
public class User {

	private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String role;
	private String accountAction;
	private String accountSwitch;
}
