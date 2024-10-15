package com.mattjoneslondon.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import com.mattjoneslondon.rulebook.domain.LoanApplicant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
class HomeLoanRateRuleBookTest {

    @Test
    void creditScoreUnderSixHundredHas4xRateIncrease() {
        var defaultRate = 4.5;
        RuleBook<Double> rulebook = RuleBookBuilder
                .create(HomeLoanRateRuleBook.class)
                .withResultType(Double.class)
                .withDefaultResult(defaultRate)
                .build();
        NameValueReferableMap<LoanApplicant> facts = new FactMap<>();
        facts.setValue("applicant", new LoanApplicant(10, 20000.0, true));
        rulebook.run(facts);
        assertThat(rulebook.getResult().orElseThrow().getValue(), equalTo(18.0));
    }
}