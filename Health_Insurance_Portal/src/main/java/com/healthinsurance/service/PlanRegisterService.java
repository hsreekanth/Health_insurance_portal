package com.healthinsurance.service;

import java.util.List;

import com.healthinsurance.model.PlanRegistration;

public interface PlanRegisterService  {

	public String savePlanDetails(PlanRegistration planDetails);
	public PlanRegistration updatePlanDetailsBasedOnPlanId(Integer planId);
	public List<PlanRegistration> getAllPlanDetails();
	public PlanRegistration checkAccountActivationSwitchToDeleteOrActivate(Integer planId);
}
