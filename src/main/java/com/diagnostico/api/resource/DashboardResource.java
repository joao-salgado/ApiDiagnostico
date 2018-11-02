package com.diagnostico.api.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.model.CompanyProcess;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.BWPersonalSectionRepository;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.BWSectionRepository;
import com.diagnostico.api.repository.CompanyProcessRepository;
import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.repository.UserAccountRepository;
import com.diagnostico.api.repository.dto.BWByProcess;
import com.diagnostico.api.repository.dto.CompanyStatisticsBwDTO;
import com.diagnostico.api.repository.dto.RearcherStatisticsBwDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

@RestController
@RequestMapping("/statistics")
public class DashboardResource {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyProcessRepository companyProcessRepository;
	
	@Autowired
	private BWQuestionnaireRepository bwQuestionnaireRepository;
	
	@Autowired
	private BWSectionRepository bwSectionRepository;
	
	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private  BWPersonalSectionRepository bwPersonalSectionRepository;
	
	@GetMapping("companies/{companyId}/{diagnosis}")
	@PreAuthorize("hasAuthority('ROLE_COMPANY_DASHBOARD')")
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
	
	@GetMapping("/rearchers/{diagnosis}")
	@PreAuthorize("hasAuthority('ROLE_RESEARCH_DASHBOARD')")
	public Object getForRearcher(@PathVariable String diagnosis) {
		

		switch (diagnosis) {
			case "bw":
				
				List<CompanyProcess> processes = companyProcessRepository.findAll();
				List<BWByProcess> all = new ArrayList<BWByProcess>();
				
				processes.stream().forEach(process -> {
										
					BWByProcess bwByProcess = new BWByProcess();
					bwByProcess.setName(process.getName());
					bwByProcess.setCount(bwQuestionnaireRepository.countByCompanyCompanyProcessId(process.getId()));
					bwByProcess.setTotal(bwQuestionnaireRepository.avgTotalResultByProcess(process.getId()));
					
					bwByProcess.setGet(bwSectionRepository.avgTotalBySectionByProcess(1, process.getId()));
					bwByProcess.setUse(bwSectionRepository.avgTotalBySectionByProcess(2, process.getId()));
					bwByProcess.setLearn(bwSectionRepository.avgTotalBySectionByProcess(3, process.getId()));
					bwByProcess.setContribute(bwSectionRepository.avgTotalBySectionByProcess(4, process.getId()));
					bwByProcess.setEvaluate(bwSectionRepository.avgTotalBySectionByProcess(5, process.getId()));
					bwByProcess.setBuild(bwSectionRepository.avgTotalBySectionByProcess(6, process.getId()));
					bwByProcess.setDiscard(bwSectionRepository.avgTotalBySectionByProcess(7, process.getId()));
					
					all.add(bwByProcess);
				});
				
				
				Long companiesCount = companyRepository.count();
				Long usersCount = userRepository.count();
				
				return new RearcherStatisticsBwDTO(
						companiesCount,
						bwQuestionnaireRepository.countDistinctCompanyIdByStatus(QuestionnaireStatus.CLOSED),
						bwQuestionnaireRepository.countDistinctCompanyIdByStatus(QuestionnaireStatus.CANCELED),
						bwQuestionnaireRepository.countByStatus(QuestionnaireStatus.OPEN),
						bwQuestionnaireRepository.countByStatus(QuestionnaireStatus.CLOSED),
						bwQuestionnaireRepository.countByStatus(QuestionnaireStatus.CANCELED),
						usersCount,
						usersCount/companiesCount,
						all
				);
				
			default:
				return null;
		}
	}

}
