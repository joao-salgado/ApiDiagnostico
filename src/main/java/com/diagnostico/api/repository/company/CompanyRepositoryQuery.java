package com.diagnostico.api.repository.company;

import com.diagnostico.api.repository.dto.CompanyStatisticsDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

public interface CompanyRepositoryQuery {
	
	public CompanyStatisticsDTO getCompanyStatistics(CompanyStatisticsFilter companyStatisticsFilter);

}
