package com.donor.crm.specification;

import com.donor.crm.model.Donor;
import com.donor.crm.model.DonorCategory;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class DonorSpecification {

    public static Specification<Donor> filter(String name, String email, DonorCategory donorCategory){
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if(name != null && !name.isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"
                ));
            }

            if(email != null && !email.isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"
                ));
            }

            if (donorCategory != null){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                        root.get("donorCategory"), donorCategory
                ));
            }

            return predicate;
        });
    }
}
