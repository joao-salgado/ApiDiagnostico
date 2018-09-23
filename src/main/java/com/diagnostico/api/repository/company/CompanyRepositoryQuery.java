package com.diagnostico.api.repository.company;

import java.util.List;

import com.diagnostico.api.repository.dto.CompanyStatisticsBwDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

public interface CompanyRepositoryQuery {
	
	public List<CompanyStatisticsBwDTO> getCompanyStatisticsBw(CompanyStatisticsFilter companyStatisticsFilter);

}
