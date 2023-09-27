package com.alura.modelo.enums;

public enum CourseCategories {
    FRONTEND("Frontend"),
    BACKEND("Backend"),
    DATA_ANALyTIC("Data Analytic"),
    MACHINE_LEARNING("Machine learning");

    final String catDbName;

    CourseCategories(String catDbName) {
        this.catDbName = catDbName;
    }

    @Override
    public String toString() {
        return catDbName ;
    }
}
