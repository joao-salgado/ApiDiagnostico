package com.diagnostico.api.repository.company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.diagnostico.api.repository.dto.CompanyStatisticsDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

public class CompanyRepositoryImpl implements CompanyRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public CompanyStatisticsDTO getCompanyStatistics(CompanyStatisticsFilter companyStatisticsFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
