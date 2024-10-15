package com.mattjoneslondon.rulebook.domain;

public record LoanApplicant(int creditScore, double cashOnHand, boolean firstTimeHomeBuyer) {
}