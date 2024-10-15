package com.mattjoneslondon.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.mattjoneslondon.rulebook.domain.ApplicantBean;
import org.junit.jupiter.api.Test;

class HomeLoanRateRuleBookTest {

    @Test
    void runRules() {
        RuleBook<Double> rulebook = RuleBookBuilder
                .create(HomeLoanRateRuleBook.class)
                .withResultType(Double.class)
                .withDefaultResult(4.5)
                .build();
        NameValueReferableMap<ApplicantBean> facts = new FactMap<>();
        facts.setValue("applicant", new ApplicantBean(650, 20000.0, true));
        rulebook.run(facts);
        rulebook.getResult()
                .ifPresent(result -> System.out.println("Applicant qualified for the following rate: " + result));
    }
}