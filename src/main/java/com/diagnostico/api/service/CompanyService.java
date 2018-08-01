package com.diagnostico.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.diagnostico.api.model.Company;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.service.util.BeanUtilsProperties;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserAccountService userService;

	public Company create(Company company) {
		
		Company savedCompany = companyRepository.save(company);
		
		UserAccount user = company.getUserAccount().get(0);
		user.setCompany(new Company());
		user.getCompany().setId(savedCompany.getId());
		
		userService.create(user);
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
