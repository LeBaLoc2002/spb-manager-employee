package com.example.managerEmployees.model.enums;

public enum EnumPosition {
    EXECUTIVE("Executive"),
    DEPUTY("Deputy"),
    STAFF("Staff"),
    CHIEF_ACCOUNTANT("Chief accountant"),
    MANAGER("Manager");

    private final String value;

    EnumPosition(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
