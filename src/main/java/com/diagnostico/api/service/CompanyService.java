package com.diagnostico.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.diagnostico.api.model.Company;
import com.diagnostico.api.model.CompanyProcess;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.CompanyProcessRepository;
import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.service.util.BeanUtilsProperties;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyProcessRepository companyProcessRepository;
	
	@Autowired
	private UserAccountService userService;

	public Company create(Company company) {
		
		UserAccount user = company.getUserAccount().get(0);
		userService.verifyExistingUserByEmail(user.getEmail());
		
		/**
		 * VERIFICA SE O PROCESSO SELECIONADO FOI DO TIPO "OUTRO"
		 */
		if(company.getCompanyProcess().getId() == 9) {
			String name = company.getMeta().get("other_company_process").asText();
			if(!name.isEmpty()) {
				CompanyProcess newProcess = companyProcessRepository.save(new CompanyProcess(name));
				company.getCompanyProcess().setId(newProcess.getId());
			}
		}
		
		Company savedCompany = companyRepository.save(company);
		
		user.setCompany(new Company());
		user.getCompany().setId(savedCompany.getId());
		
		userService.createByCompany(user);
		return savedCompany;
	}
	
	public Company update(UUID id, Company company) {
		
		Company companySearched = searchCompany(id);
		String[] ignoredProperties = { "id" };
		BeanUtilsProperties.myCopyProperties(company, companySearched, ignoredProperties);
				
		return companyRepository.save(company);
	}
	
	private Company searchCompany(UUID id) {
		Optional<Company> companyOptional = companyRepository.findById(id);

		if (!companyOptional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		return companyOptional.get();
	}

}
