package com.healthinsurance.entities;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRegistrationRepo extends JpaRepository<PlanRegistrationEntity, Serializable> {

	public PlanRegistrationEntity findByPlanId(Integer planId);
}
