package com.atm;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
	private List<String> history;

	public Transaction() {
		history = new ArrayList<>();
	}

	public void addTransaction(String detail) {
		history.add(detail);
	}

	public void displayHistory() {
		if (history.isEmpty()) {
			System.out.println("No transactions yet.");
		} else {
			System.out.println("Transaction History:");
			for (String record : history) {
				System.out.println(record);
			}
		}
	}
}
