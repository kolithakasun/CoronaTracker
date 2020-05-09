package com.dmkk.coronatracker.ui.ui.countries;

import androidx.appcompat.app.AppCompatActivity;
import com.dmkk.coronatracker.R;
import android.os.Bundle;

import com.dmkk.coronatracker.ui.ui.countries.CountriesFragment;

public class countries  {

    String country;
    String NewConfirmed;
    String TotalConfirmed;
    String NewDeaths;
    String TotalDeaths;
    String NewRecovered;
    String TotalRecoverd;
    String Date;
    String countrycode;

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public countries() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        NewConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        NewDeaths = newDeaths;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        NewRecovered = newRecovered;
    }

    public String getTotalRecoverd() {
        return TotalRecoverd;
    }

    public void setTotalRecoverd(String totalRecoverd) {
        TotalRecoverd = totalRecoverd;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
