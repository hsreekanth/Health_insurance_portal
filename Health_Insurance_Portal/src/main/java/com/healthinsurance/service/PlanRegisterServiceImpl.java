package com.healthinsurance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthinsurance.entities.PlanRegistrationEntity;
import com.healthinsurance.entities.PlanRegistrationRepo;
import com.healthinsurance.model.PlanRegistration;

@Service
public class PlanRegisterServiceImpl implements PlanRegisterService {

	@Autowired
	private PlanRegistrationRepo repo;

	@Override
	public String savePlanDetails(PlanRegistration planDetails) {
		PlanRegistrationEntity entity = new PlanRegistrationEntity();
		BeanUtils.copyProperties(planDetails, entity);
		entity.setActiveSwitch("N");
		PlanRegistrationEntity isSaved = repo.save(entity);
		if (isSaved != null) {
			return "plan Saved Sucessfully";
		}
		return "plan not saved sucessfully";
	}

	@Override
	public PlanRegistration updatePlanDetailsBasedOnPlanId(Integer planId) {
		PlanRegistrationEntity entity = repo.findByPlanId(planId);
		PlanRegistration reg = new PlanRegistration();
		BeanUtils.copyProperties(entity, reg);
		return reg;
	}

	@Override
	public List<PlanRegistration> getAllPlanDetails() {
		List<PlanRegistrationEntity> allPlanDetails = repo.findAll();
		List<PlanRegistration> planDetails = allPlanDetails.stream().map(entity -> {
			PlanRegistration reg = new PlanRegistration();
			BeanUtils.copyProperties(entity, reg);
			return reg;
		}).collect(Collectors.toList());
		return planDetails;
	}

	@Override
	public PlanRegistration checkAccountActivationSwitchToDeleteOrActivate(Integer planId) {
		PlanRegistration planDetails = new PlanRegistration();
		PlanRegistrationEntity entity = repo.findByPlanId(planId);
		if (entity.getActiveSwitch().equalsIgnoreCase("N")) {
			entity.setActiveSwitch("Y");
		} else {
			entity.setActiveSwitch("N");
		}
		PlanRegistrationEntity savedEntity = repo.save(entity);
		BeanUtils.copyProperties(savedEntity, planDetails);
		return planDetails;
	}

}
