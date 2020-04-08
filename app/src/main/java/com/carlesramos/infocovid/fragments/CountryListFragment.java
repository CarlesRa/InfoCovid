package com.carlesramos.infocovid.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.carlesramos.infocovid.R;
import com.carlesramos.infocovid.adapters.CountryAdapter;
import com.carlesramos.infocovid.apiclient.APIUtils;
import com.carlesramos.infocovid.interficies.IApiInterface;
import com.carlesramos.infocovid.interficies.ICountrySelected;
import com.carlesramos.infocovid.model.CountryInfo;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment implements ICountrySelected {

    public static final String EXTRA_PAIS = "com.carlesramos.extrapais";

    private IApiInterface mApiInterface;
    private ArrayList<CountryInfo> countries;
    private CountryAdapter adapter;
    private RecyclerView rvCountryList;
    private ProgressBar pbListCountry;
    private TextView tvSearchText;

    public CountryListFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pbListCountry = view.findViewById(R.id.pbListCountry);
        tvSearchText = view.findViewById(R.id.tvSerchText);
        rvCountryList = view.findViewById(R.id.rvCountryList);

        pbListCountry.setVisibility(View.VISIBLE);



        tvSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    onResume();
                    adapter.filterLIst(countries);
                }
                else {
                    filterText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tvSearchText.getText().length() > 0) {
            tvSearchText.setText("");
        }
        mApiInterface = APIUtils.getIApiInterface();
        mApiInterface.getAllCountries().enqueue(new Callback<ArrayList<CountryInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryInfo>> call,
                                   Response<ArrayList<CountryInfo>> response) {

                if (response.isSuccessful()) {

                    pbListCountry.setVisibility(View.GONE);

                    countries = response.body();

                    DividerItemDecoration itemDecoration = new DividerItemDecoration(rvCountryList.
                            getContext(), DividerItemDecoration.VERTICAL);
                    itemDecoration.setDrawable(getContext().getResources().
                            getDrawable(R.drawable.item_decoration));

                    adapter = new CountryAdapter(countries, getActivity(),
                            CountryListFragment.this);
                    rvCountryList.setAdapter(adapter);
                    rvCountryList.setHasFixedSize(true);
                    rvCountryList.addItemDecoration(itemDecoration);
                    rvCountryList.setLayoutManager(new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CountryInfo>> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public Object getReturnTransition() {
        return super.getReturnTransition();
    }

    public void filterText(String text) {

        ArrayList<CountryInfo> filteredNames = new ArrayList<>();
        for (CountryInfo ci: countries) {
            System.out.println(ci.getName());
            if (ci.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredNames.add(ci);
            }
        }
        adapter.filterLIst(filteredNames);

    }

    @Override
    public void onItemSelected(int position, View v) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PAIS, countries.get(position));

        Navigation.findNavController(v).
                navigate(R.id.action_countryListFragment_to_countryDetailFragment, bundle);
    }
}
