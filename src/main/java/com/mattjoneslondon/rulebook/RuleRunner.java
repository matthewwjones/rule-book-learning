package com.mattjoneslondon.rulebook;

import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;

public class RuleRunner {

    public void runRules(NameValueReferableMap<String> factMap) {
        RuleBook ruleBook = RuleBookBuilder.create()
                .addRule(rule -> rule.withFactType(String.class)
                        .when(f -> f.containsKey("hello"))
                        .using("hello")
                        .then(System.out::print))
                .addRule(rule -> rule.withFactType(String.class)
                        .when(f -> f.containsKey("world"))
                        .using("world")
                        .then(System.out::println))
                .build();
        ruleBook.run(factMap);
    }
}
