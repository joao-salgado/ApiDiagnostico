package com.diagnostico.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.repository.BWPersonalSectionRepository;
import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.repository.dto.CompanyStatisticsBwDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

@RestController
@RequestMapping("/statistics")
public class DashboardResource {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private  BWPersonalSectionRepository bwPersonalSectionRepository;
	
	@GetMapping("companies/{companyId}/{diagnosis}")
	public List<?> getCompanyStatistics(@PathVariable UUID companyId, @PathVariable String diagnosis, CompanyStatisticsFilter companyStatisticsFilter) {

		switch (diagnosis) {
			case "bw":
				List<CompanyStatisticsBwDTO> list = companyRepository.getCompanyStatisticsBw(companyStatisticsFilter);
				
				list.stream().forEach(item -> {
					item.setBwPersonalSection(bwPersonalSectionRepository.findByBwPersonalQuestionnaireId(item.getDiagnosisPersonalId()));
				});
				
				return list;
				
			default:
				return null;
		}
		
		
	}

}
