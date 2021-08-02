package com.cs446.covidsafe.ui.Covid.CovidInfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

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
import com.cs446.covidsafe.model.Question;
import com.cs446.covidsafe.model.QuestionFactory;
import com.cs446.covidsafe.model.TravelRestrictions;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private List<Question> questionList = new ArrayList<Question>();

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
        // TODO: add logic here:
        // When the user clicks the button, it should jump to the covid stats page
        Button jumpCovidStatsButton = (Button) rootView.findViewById(R.id.jumpCovidStatsButton);
        jumpCovidStatsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    private void setCovidUpdatesLink() {
        // TODO: add logic here:
        // When the user clicks the button, it should jump to the covid updates page
        Button jumpCovidUpdatesButton = (Button) rootView.findViewById(R.id.jumpCovidUpdatesButton);
        jumpCovidUpdatesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

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

            }
        });
        country_selection.setSelection(2);
    }

    public void registerExpandEvent() {
        //Implement the expandable item list function
        //For covid info section
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

        //For COVID transmission info section
        ImageButton button2 = (ImageButton) rootView.findViewById(R.id.expandTransmissionInfoButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.transmission);
                Button bv = (Button) rootView.findViewById(R.id.jumpCovidStatsButton);
                if(tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    bv.setVisibility(View.VISIBLE);
                    button2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }else {
                    tv.setVisibility(View.GONE);
                    bv.setVisibility(View.GONE);
                    button2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });

        //For COVID symptoms info section
        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.expandSymptomsInfoButton);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv1 = (TextView) rootView.findViewById(R.id.symptom1);
                TextView tv2 = (TextView) rootView.findViewById(R.id.symptom2);
                TextView tv3 = (TextView) rootView.findViewById(R.id.symptom3);
                TextView tv4 = (TextView) rootView.findViewById(R.id.symptom4);
                if (tv1.getVisibility() == View.GONE) {
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    tv4.setVisibility(View.VISIBLE);
                    button3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    tv4.setVisibility(View.GONE);
                    button3.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }

            }
        });

        //For COVID test info section
        ImageButton button4 = (ImageButton) rootView.findViewById(R.id.expandTestInfoButton);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.testinfo);
                Button bv = (Button) rootView.findViewById(R.id.jumpCovidUpdatesButton);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    bv.setVisibility(View.VISIBLE);
                    button4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv.setVisibility(View.GONE);
                    bv.setVisibility(View.GONE);
                    button4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });

        //For keep safe info section
        ImageButton button5 = (ImageButton) rootView.findViewById(R.id.expandKeepSafeButton);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.keepSafeInfoText);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    button5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv.setVisibility(View.GONE);
                    button5.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });

        //For mask info section
        ImageButton button6 = (ImageButton) rootView.findViewById(R.id.expandMaskInfoButton);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.maskInfoText);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    button6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv.setVisibility(View.GONE);
                    button6.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });

        //For isolation info section
        ImageButton button7 = (ImageButton) rootView.findViewById(R.id.expandIsolationInfoButton);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.isolationInfoText);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    button7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv.setVisibility(View.GONE);
                    button7.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });

        //For treatments info section
        ImageButton button8 = (ImageButton) rootView.findViewById(R.id.expandTreatmentsInfoButton);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) rootView.findViewById(R.id.treatmentsInfoText);
                if (tv.getVisibility() == View.GONE) {
                    tv.setVisibility(View.VISIBLE);
                    button8.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                } else {
                    tv.setVisibility(View.GONE);
                    button8.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                }
            }
        });
    }

    private void setWhoWebLink() {
        //When the user clicks the button, it should jump to who website
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

    private void registerMarkCalculation() {
        Button submitButton = (Button) rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int mark = 0;
                for(Question question : questionList){
                    mark += question.validateAnswer(rootView, getContext());
                }
                TextView tv = (TextView) rootView.findViewById(R.id.mark);
                tv.setText("Your mark is: " + Integer.toString(mark) + " !");
                tv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setButtonLinks(){
        //Register events for buttons
        setWhoWebLink();
        setCovidStatsLink();
        setCovidUpdatesLink();
        registerExpandEvent();
        registerMarkCalculation();
    }

    private void fillDataSource(){
        //Fill data sources
        fillCovidNews();
        fillSpinnerSource();
    }

    private void fillQuestions(){

        QuestionFactory qf = new QuestionFactory();
        for(int i = 0; i < 6; i++){
            Question q = qf.getQuestion(i + 1);
            q.updateQuestion(rootView, this.getContext());
            questionList.add(q);
        }
    }

    private void calculateMarks() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_covid_info, container, false);
        fillDataSource();
        setButtonLinks();
        fillQuestions();
        return rootView;
    }
}