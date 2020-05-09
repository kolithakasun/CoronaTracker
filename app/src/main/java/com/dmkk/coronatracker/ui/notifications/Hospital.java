package com.dmkk.coronatracker.ui.notifications;

public class Hospital {

    String id;
    String name;
    String namesi;
    String update;
    String cumulative_local;
    String cumulative_foreign;
    String treatment_local;
    String treatment_foreign;
    String cumulative_total;
    String treatment_total;

    public Hospital() {
    }

    public Hospital(String id, String name, String namesi, String update, String cumulative_local, String cumulative_foreign, String treatment_local, String treatment_foreign, String cumulative_total, String treatment_total) {
        this.id = id;
        this.name = name;
        this.namesi = namesi;
        this.update = update;
        this.cumulative_local = cumulative_local;
        this.cumulative_foreign = cumulative_foreign;
        this.treatment_local = treatment_local;
        this.treatment_foreign = treatment_foreign;
        this.cumulative_total = cumulative_total;
        this.treatment_total = treatment_total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamesi() {
        return namesi;
    }

    public void setNamesi(String namesi) {
        this.namesi = namesi;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getCumulative_local() {
        return cumulative_local;
    }

    public void setCumulative_local(String cumulative_local) {
        this.cumulative_local = cumulative_local;
    }

    public String getCumulative_foreign() {
        return cumulative_foreign;
    }

    public void setCumulative_foreign(String cumulative_foreign) {
        this.cumulative_foreign = cumulative_foreign;
    }

    public String getTreatment_local() {
        return treatment_local;
    }

    public void setTreatment_local(String treatment_local) {
        this.treatment_local = treatment_local;
    }

    public String getTreatment_foreign() {
        return treatment_foreign;
    }

    public void setTreatment_foreign(String treatment_foreign) {
        this.treatment_foreign = treatment_foreign;
    }

    public String getCumulative_total() {
        return cumulative_total;
    }

    public void setCumulative_total(String cumulative_total) {
        this.cumulative_total = cumulative_total;
    }

    public String getTreatment_total() {
        return treatment_total;
    }

    public void setTreatment_total(String treatment_total) {
        this.treatment_total = treatment_total;
    }
}
