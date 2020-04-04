package com.carlesramos.infocovid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.carlesramos.infocovid.R;
import com.carlesramos.infocovid.interficies.ICountrySelected;
import com.carlesramos.infocovid.model.CountryInfo;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import static com.carlesramos.infocovid.utils.GenericValues.CANTIDAD_ALTA_CASOS;
import static com.carlesramos.infocovid.utils.GenericValues.CANTIDAD_MEDIA_CASOS;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterViewHolder>{

    private ArrayList<CountryInfo> countries;
    private Context context;
    private ICountrySelected listener;

    public CountryAdapter(ArrayList<CountryInfo> countries, Context context,
                          ICountrySelected listener) {

        this.countries = countries;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_country, parent, false);
        return new CountryAdapterViewHolder(itemView, context, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapterViewHolder holder, int position) {
        holder.bindCountry(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryAdapterViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private final DecimalFormat FORMATTER = (DecimalFormat) NumberFormat.getInstance(Locale.UK);
        private ICountrySelected listener;
        private Context context;
        private TextView tvCountryListName;
        private TextView tvListCases;

        private CountryAdapterViewHolder(@NonNull View itemView, Context context,
                                        ICountrySelected listener) {
            super(itemView);
            this.listener = listener;
            this.context = context;
            tvCountryListName = itemView.findViewById(R.id.tvCountryListName);
            tvListCases = itemView.findViewById(R.id.tvListCases);
            itemView.setOnClickListener(this);
        }

        private void bindCountry(CountryInfo countryInfo) {

            tvCountryListName.setText(countryInfo.getName());
            tvListCases.setText(FORMATTER.format(countryInfo.getCases()));
            if (countryInfo.getCases() >= CANTIDAD_ALTA_CASOS) {
                tvCountryListName.setTextColor(context.getResources().getColor(R.color.muerte));
            }
            else if (countryInfo.getCases() >= CANTIDAD_MEDIA_CASOS) {
                tvCountryListName.setTextColor(context.getResources().getColor(R.color.neutrales));
            }
            else if (countryInfo.getCases() < CANTIDAD_MEDIA_CASOS) {
                tvCountryListName.setTextColor(context.getResources().getColor(R.color.recuperados));
            }
        }

        @Override
        public void onClick(View v) {
            listener.onItemSelected(getLayoutPosition(),v);
        }

    }

    public void filterLIst(ArrayList<CountryInfo> countriesFiltered) {
        countries.clear();
        this.countries.addAll(countriesFiltered);
        notifyDataSetChanged();

    }
}
