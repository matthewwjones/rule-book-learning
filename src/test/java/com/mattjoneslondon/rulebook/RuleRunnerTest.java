package com.mattjoneslondon.rulebook;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleRunnerTest {

    @Test
    void runRules() {
        NameValueReferableMap<String> factMap = new FactMap<>();
        factMap.setValue("hello", "Hello ");
        factMap.setValue("world", " World");
        var ruleRunner = new RuleRunner();
        ruleRunner.runRules(factMap);
    }
}