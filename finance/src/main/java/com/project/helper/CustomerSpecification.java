package com.project.helper;

import com.project.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class CustomerSpecification implements Specification<Customer> {

    private final SearchCriteria searchCriteria;

    public CustomerSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.
                getSimpleOperation(searchCriteria.getOperation()))) {

            case CONTAINS:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.like(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.like(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.like(cb.lower(principalJoin(root).<String>get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else {
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }

            case DOES_NOT_CONTAIN:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.notLike(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.notLike(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.notLike(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                } else {
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }

            case EQUAL:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.equal(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.equal(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.equal(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.equal(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

            case NOT_EQUAL:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.notEqual(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.notEqual(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.notEqual(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.notEqual(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

            case BEGINS_WITH:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.like(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.like(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.like(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else {
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                }

            case ENDS_WITH:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.like(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.like(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.like(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else {
                    return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                }

            case DOES_NOT_BEGIN_WITH:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.notLike(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.notLike(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.notLike(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                } else {
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch + "%");
                }

            case DOES_NOT_END_WITH:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.notLike(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.notLike(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.notLike(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                } else {
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())),
                            "%" + strToSearch);
                }

            case GREATER_THAN:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.greaterThan(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.greaterThan(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.greaterThan(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.greaterThan(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

            case GREATER_THAN_EQUAL:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.greaterThanOrEqualTo(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.greaterThanOrEqualTo(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.greaterThanOrEqualTo(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.greaterThanOrEqualTo(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

            case LESS_THAN:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.lessThan(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.lessThan(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.lessThan(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.lessThan(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

            case LESS_THAN_EQUAL:
                if (searchCriteria.getDomain().equals("address")) {
                    return cb.lessThanOrEqualTo(cb.lower(addressJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("phoneNumber")) {
                    return cb.lessThanOrEqualTo(cb.lower(phoneNumberJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else if (searchCriteria.getDomain().equals("principal")) {
                    return cb.lessThanOrEqualTo(cb.lower(principalJoin(root).get(searchCriteria.getFilterKey())),
                            strToSearch);
                } else {
                    return cb.lessThanOrEqualTo(cb.lower(root.get(searchCriteria.getFilterKey())),
                            strToSearch);
                }

        }

        return null;
    }

    private Join<Object, Object> addressJoin(Root<Customer> root) {
        return root.join("addresses");
    }

    private Join<Object, Object> phoneNumberJoin(Root<Customer> root) {
        return root.join("phoneNumbers");
    }

    private Join<Object, Object> principalJoin(Root<Customer> root) {
        return root.join("principal");
    }
}

