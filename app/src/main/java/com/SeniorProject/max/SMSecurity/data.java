package com.SeniorProject.max.SMSecurity;
class Data {
    private String foobar = null;

    public boolean isReady() {
        return (foobar != null);
    }

    @Override
    public String toString() {
        return "Data [Foobar=" + foobar + "]";
    }
}