package com.carlesramos.infocovid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;
import com.carlesramos.infocovid.R;
import com.carlesramos.infocovid.apiclient.APIUtils;
import com.carlesramos.infocovid.interficies.IApiInterface;
import com.carlesramos.infocovid.model.AllCountriesInfo;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private final String TAG = "MainFragment";

    private IApiInterface mApiInterface;
    private AllCountriesInfo allCountriesInfo;
    private Toolbar toolbar;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final TextView tvWorldCases = view.findViewById(R.id.tvWorldCasaes);
        final TextView tvWorldDeaths = view.findViewById(R.id.tvWorldDeaths);
        final TextView tvWorldRecovered = view.findViewById(R.id.tvWorldRecovered);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        final Button btByCountry = view.findViewById(R.id.btByCountry);
        final DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.UK);
        final DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        progressBar.setVisibility(View.VISIBLE);

        mApiInterface = APIUtils.getIApiInterface();
        mApiInterface.getGlobalInfo().enqueue(new Callback<AllCountriesInfo>() {
            @Override
            public void onResponse(Call<AllCountriesInfo> call, Response<AllCountriesInfo> response) {
                Log.i(TAG, "OnResponse OK!");
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    allCountriesInfo = response.body();
                    tvWorldCases.setText(formatter.format(allCountriesInfo.getCases()));
                    tvWorldDeaths.setText(formatter.format(allCountriesInfo.getDeaths()));
                    tvWorldRecovered.setText(formatter.format(allCountriesInfo.getRecovered()));
                }
            }

            @Override
            public void onFailure(Call<AllCountriesInfo> call, Throwable t) {
                Log.i(TAG, "onFaliure");
            }
        });

        btByCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_countryListFragment);
            }
        });

    }

   /* @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //menu.findItem(R.id.menu_search).setVisible(false);
    }*/

    /* public void setToolbar(View view){
        setHasOptionsMenu(true);
        toolbar = view.findViewById(R.id.appbar);
        ((AppCompatActivity) this.getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menuapp);
    }*/
}
