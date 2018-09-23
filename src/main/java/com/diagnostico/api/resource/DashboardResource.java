package com.diagnostico.api.resource;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

@RestController
@RequestMapping("/statistics")
public class DashboardResource {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping("companies/{companyId}/{diagnosis}")
	public Object getCompanyBwStatistics(@PathVariable UUID companyId, @PathVariable String diagnosis, CompanyStatisticsFilter companyStatisticsFilter) {
		return (Object) companyRepository.getCompanyStatistics(companyStatisticsFilter);
	}

}
