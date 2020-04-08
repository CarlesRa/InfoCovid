package com.carlesramos.infocovid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.carlesramos.infocovid.R;
import com.carlesramos.infocovid.model.CountryInfo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static com.carlesramos.infocovid.fragments.CountryListFragment.EXTRA_PAIS;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryDetailFragment extends Fragment {

    private TextView tvNameDetail;
    private TextView tvTotalCases;
    private TextView tvTodayCases;
    private TextView tvDeaths;
    private TextView tvTodayDeaths;
    private TextView tvRecovered;
    private TextView tvActiveCases;
    private TextView tvCritical;
    private TextView tvCasesPerMillion;
    private TextView tvDeathsPerMillion;
    private DecimalFormat formatter;


    public CountryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CountryInfo country;

        formatter= (DecimalFormat) NumberFormat.getInstance(Locale.UK);
        final DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        initializeComponents(view);
        country = (CountryInfo) getArguments().getSerializable(EXTRA_PAIS);
        if (country != null) {
            setUpTexts(country);
        }

    }


    private void initializeComponents(View view) {

        tvNameDetail = view.findViewById(R.id.tvDetailCountryName);
        tvTotalCases= view.findViewById(R.id.tvTotalCasesDetail);
        tvTodayCases = view.findViewById(R.id.tvTodayCases);
        tvDeaths = view.findViewById(R.id.tvDeathsDetail);
        tvTodayDeaths = view.findViewById(R.id.tvTodayDeathsDetail);
        tvRecovered = view.findViewById(R.id.tvRecoveredDetail);
        tvActiveCases = view.findViewById(R.id.tvActiveCases);
        tvCritical = view.findViewById(R.id.tvCriticalCases);
        tvCasesPerMillion = view.findViewById(R.id.tvCasesPerMilion);
        tvDeathsPerMillion = view.findViewById(R.id.tvDeathsPerMillion);

    }

    private void setUpTexts(CountryInfo country) {
        tvNameDetail.setText(country.getName());
        tvTotalCases.setText(formatter.format(country.getCases()));
        tvTodayCases.setText(formatter.format(country.getTodayCases()));
        tvDeaths.setText(formatter.format(country.getDeaths()));
        tvTodayDeaths.setText(formatter.format(country.getTodayDeaths()));
        tvRecovered.setText(formatter.format(country.getRecovered()));
        tvActiveCases.setText(formatter.format(country.getActive()));
        tvCritical.setText(formatter.format(country.getCritical()));
        tvCasesPerMillion.setText(formatter.format(country.getCasesPerOneMillion()));
        tvDeathsPerMillion.setText(formatter.format((country.getCasesPerOneMillion())));

    }
}
