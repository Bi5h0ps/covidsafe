package com.cs446.covidsafe.ui.Covid.CovidInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.CovidNews;
import com.cs446.covidsafe.model.TravelRestrictions;
import com.google.gson.Gson;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CovidInfoFragment extends Fragment {

    View rootView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CovidInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CovidInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CovidInfoFragment newInstance(String param1, String param2) {
        CovidInfoFragment fragment = new CovidInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    public void fillCovidNews(){
        CovidNews covid_news = new CovidNews();
        String[] from = {"news_image", "news_title", "weblink"};
        int[] to = {R.id.news_image, R.id.news_title, R.id.weblink};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), covid_news.getCovidNewsList(),
                R.layout.covid_news_listview_item, from, to) {
            @Override
            public void setViewImage(final ImageView v, final String value) {
                Picasso.get().load(value).into(v);
            }
        };
        covid_news.pullNews(adapter);
        ListView covid_news_list = rootView.findViewById(R.id.covid_news_list);
        covid_news_list.setAdapter(adapter);
        covid_news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = ((TextView) view.findViewById(R.id.weblink)).getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void setCovidStatsLink(){
        Button who_web_button = (Button) rootView.findViewById(R.id.who_web_button);
        who_web_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.who.int/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void fillSpinnerSource(){

        TravelRestrictions tr = new TravelRestrictions();
        HashMap<String, String> restriction_data = tr.getRestrictionData();
        List<String> country_data = tr.getCountryData();

        Spinner country_selection = (Spinner) rootView.findViewById(R.id.country_selection);
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, country_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_selection.setAdapter(adapter);

        country_selection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView travel_restriction = (TextView) rootView.findViewById(R.id.travel_restriction);

                String restrictionContent = restriction_data.get(country_selection.getSelectedItem().toString());
                System.out.println(restriction_data.size());
                travel_restriction.setText(restrictionContent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        country_selection.setSelection(2);
    }

    public void registerExpandEvent() {
        ImageButton button1 = (ImageButton) rootView.findViewById(R.id.expandCovidInfoButton);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.covid_facts);
                Button bv = (Button) rootView.findViewById(R.id.who_web_button);
                if(tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    bv.setVisibility(View.VISIBLE);
                    button1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }else {
                    tv.setVisibility(View.GONE);
                    bv.setVisibility(View.GONE);
                    button1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });
    }

    private void setWhoWebLink(){
       Button who_web_button = (Button) rootView.findViewById(R.id.who_web_button);
       who_web_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.who.int/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void setButtonLinks(){
        setWhoWebLink();
        setCovidStatsLink();
        registerExpandEvent();
    }

    private void fillDataSource(){
        fillCovidNews();
        fillSpinnerSource();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_covid_info, container, false);
        fillDataSource();
        setButtonLinks();
        return rootView;
    }
}