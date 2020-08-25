package com.healthinsurance.model;

import lombok.Data;

@Data
public class PlanRegistration {
	private int planId;
	private String planName;
	private String planDescription;
	private String startDate;
	private String endDate;
	private String activeSwitch;

}
