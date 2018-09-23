package com.diagnostico.api.repository.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.dto.CompanyStatisticsBwDTO;
import com.diagnostico.api.repository.filter.CompanyStatisticsFilter;

public class CompanyRepositoryImpl implements CompanyRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CompanyStatisticsBwDTO> getCompanyStatisticsBw(CompanyStatisticsFilter companyStatisticsFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CompanyStatisticsBwDTO> criteria = builder.createQuery(CompanyStatisticsBwDTO.class);
		Root<UserAccount> root = criteria.from(UserAccount.class);
		
		Join<Object, Object> bwPersonalQuestionnaires = root.join("bwPersonalQuestionnaires");
		Join<Object, Object> bwQuestionnaire = bwPersonalQuestionnaires.join("bwQuestionnaire");
		
		criteria.select(builder.construct(CompanyStatisticsBwDTO.class,
										bwPersonalQuestionnaires.get("id"),
										bwPersonalQuestionnaires.get("totalResult"),
										bwPersonalQuestionnaires.get("created")/*,
										bwPersonalQuestionnaires.get("bwPersonalSection")*/
						))
				.distinct(true);
		
		
		Predicate[] predicates = addStatisticsRestrictions(companyStatisticsFilter, builder,
															root, bwPersonalQuestionnaires,
															bwQuestionnaire);
		criteria.where(predicates);
		TypedQuery<CompanyStatisticsBwDTO> query = manager.createQuery(criteria);
				
		return (List<CompanyStatisticsBwDTO>) query.getResultList();
		
	}

	private Predicate[] addStatisticsRestrictions(CompanyStatisticsFilter companyStatisticsFilter,
						CriteriaBuilder builder,
						Root<UserAccount> root,
						Join<Object, Object> bwPersonalQuestionnaires,
						Join<Object, Object> bwQuestionnaire) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(companyStatisticsFilter.getRole())) {
			predicates.add(builder.equal(root.get("userType").get("id"), companyStatisticsFilter.getRole()));
		}

		if (companyStatisticsFilter.getStart() != null) {
			predicates.add(builder.greaterThanOrEqualTo(bwPersonalQuestionnaires.get("created"),
							companyStatisticsFilter.getStart().atTime(LocalTime.MIN)));
		}

		if (companyStatisticsFilter.getEnd() != null) {
			predicates.add(builder.lessThanOrEqualTo(bwPersonalQuestionnaires.get("created"),
							companyStatisticsFilter.getEnd().atTime(LocalTime.MAX)));
		}
		
		if (companyStatisticsFilter.getCompany() != null) {
			predicates.add(builder.equal(root.get("company").get("id"), companyStatisticsFilter.getCompany()));
		}
		
		predicates.add(builder.equal(bwQuestionnaire.get("status"), QuestionnaireStatus.CLOSED));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
