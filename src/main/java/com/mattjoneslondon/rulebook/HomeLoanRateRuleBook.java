package com.mattjoneslondon.rulebook;

import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;
import com.mattjoneslondon.rulebook.domain.LoanApplicant;

public class HomeLoanRateRuleBook extends CoRRuleBook<Double> {
    @Override
    public void defineRules() {

        //credit score under 600 gets a 4x rate increase
        addRule(RuleBuilder.create()
                .withName("Increase rate for low credit score.")
                .withFactType(LoanApplicant.class)
                .withResultType(Double.class)
                .when(facts -> facts.getOne().creditScore() < 600)
                .then((facts, result) -> result.setValue(result.getValue() * 4))
                .stop()
                .build()
        );

        //credit score between 600 and 700 pays a 1 point increase
        addRule(RuleBuilder.create()
                .withName("Add one point increase for credit score 600-700.")
                .withFactType(LoanApplicant.class)
                .withResultType(Double.class)
                .when(facts -> facts.getOne().creditScore() < 700)
                .then((facts, result) -> result.setValue(result.getValue() + 1))
                .build());

        //credit score is 700 and they have at least $25,000 cash on hand
        addRule(RuleBuilder
                .create()
                .withName("Good credit score (>700) and good deposit (>25,000)")
                .withFactType(LoanApplicant.class).withResultType(Double.class)
                .when(facts ->
                        facts.getOne().creditScore() >= 700 &&
                        facts.getOne().cashOnHand() >= 25000)
                .then((facts, result) -> result.setValue(result.getValue() - 0.25))
                .build());

        addRule(RuleBuilder.create()
                .withName("First time buyers get 20% off their rate, unless they have a credit score < 600.")
                .withFactType(LoanApplicant.class)
                .withResultType(Double.class)
                .when(facts -> facts.getOne().firstTimeHomeBuyer())
                .then((facts, result) -> result.setValue(result.getValue() * 0.80))
                .build());
    }
}