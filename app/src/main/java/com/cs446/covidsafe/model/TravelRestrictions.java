package com.cs446.covidsafe.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TravelRestrictions {
    private HashMap<String, String> travelRestrictionData;

    public HashMap<String, String> getRestrictionData(){
        return travelRestrictionData;
    }

    public List<String> getCountryData(){
        List<String> country_data = new ArrayList<String>();
        Iterator it = travelRestrictionData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            country_data.add((String) pair.getKey());
        }
        return country_data;
    }

    private void fillRestrictions(){
        travelRestrictionData = new HashMap<String, String>();
        travelRestrictionData.put("Canada", "To protect Canadians from the outbreak of COVID-19, the Prime Minister " +
                "announced travel restrictions that limit travel to Canada. Until further notice, most foreign " +
                "nationals cannot travel to Canada, even if they have a valid visitor visa or electronic travel " +
                "authorization (eTA).\n" +
                "\n" +
                "These restrictions stop most non-essential (discretionary) travel to Canada.");
        travelRestrictionData.put("China", "All travelers must present two negative tests -- PCR and antibody tests -- taken within 48 hours of travel.\n" +
                "For the newly qualified entrants, entry depends on having received two doses of Covid-19 vaccines at least 14 days prior to entry. " +
                "They must apply for a visa in advance, and show their proof of vaccination on arrival, as well as the negative tests.\n" +
                "Arrivals are screened once more at the airport. Those failing the checks will be sent to government facilities. " +
                "You must then quarantine on arrival. Some regions demand 14 days; others, 21. " +
                "This might take place at a government facility or at your home.");
        travelRestrictionData.put("US", "Effective January 26, 2021, all airline passengers to the United States " +
                "ages two years and older must provide a negative COVID-19 viral test taken within three calendar days of travel.  " +
                "Alternatively, travelers to the U.S. may provide documentation from a licensed health care provider of having " +
                "recovered from COVID-19 in the 90 days preceding travel.");
        travelRestrictionData.put("UK", "Before you travel to England you must:\n" +
                "take a COVID-19 test – you must take the test in the 3 days before you travel to England\n" +
                "book and pay for COVID-19 tests – to be taken after arrival in England\n" +
                "complete a passenger locator form\n\n" +
                "On arrival in England you must:\n" +
                "quarantine at home or in the place you are staying for 10 days\n" +
                "take a COVID-19 test on or before day 2 and on or after day 8");
        travelRestrictionData.put("Australia", "Australia’s borders are currently closed and entry to Australia " +
                "remains strictly controlled to help prevent the spread of COVID-19. " +
                "Travel to Australia is only available if you are exempt or you have been granted an individual exemption.\n" +
                "\n" +
                "Travel restrictions are subject to change in response to the circumstances surrounding COVID-19. " +
                "You can stay informed with the latest updates by checking this page regularly. " +
                "If you are transiting through Australia further information can be found on the " +
                "Transiting Australia webpage.");
        travelRestrictionData.put("New Zealand", "Who can travel to NZ:\n\n" +
                "The New Zealand borders are closed to almost all travellers to help stop the spread of COVID-19. " +
                "The travel restrictions apply to all arrivals into New Zealand by air or sea.\n" +
                "\n" +
                "New Zealand citizens and residents:\n\n" +
                "You have a legal right to come to New Zealand if you’re:\n" +
                "\n" +
                "a New Zealand citizen\n" +
                "a New Zealand resident with valid travel conditions. ");
        travelRestrictionData.put("EU", "Following a review under the recommendation on the gradual lifting of the temporary restrictions on non-essential travel into the EU, the Council updated the list of countries, special administrative regions and other entities and territorial authorities for which travel restrictions should be lifted. In particular, Rwanda and Thailand were removed from the list and Ukraine was added to the list.\n" +
                "\n" +
                "As stipulated in the Council recommendation, this list will continue to be reviewed regularly and, as the case may be, updated.\n" +
                "\n" +
                "Based on the criteria and conditions set out in the recommendation, as from 15 July 2021 member states should gradually lift the travel restrictions at the external borders for residents of the following third countries:\n" +
                "\n" +
                "Albania\n" +
                "Armenia\n" +
                "Australia\n" +
                "Azerbaijan\n" +
                "Bosnia and Hercegovina\n" +
                "Brunei Darussalam\n" +
                "Canada\n" +
                "Israel\n" +
                "Japan\n" +
                "Jordan\n" +
                "Lebanon\n" +
                "Montenegro\n" +
                "New Zealand\n" +
                "Qatar\n" +
                "Republic of Moldova\n" +
                "Republic of North Macedonia\n" +
                "Saudi Arabia\n" +
                "Serbia\n" +
                "Singapore\n" +
                "South Korea\n" +
                "Ukraine (new)\n" +
                "United States of America\n" +
                "China, subject to confirmation of reciprocity\n" +
                "Travel restrictions should also be gradually lifted for the special administrative regions of China Hong Kong and Macao.\n" +
                "\n" +
                "Under the category of entities and territorial authorities that are not recognised as states by at least one member state, travel restrictions for Kosovo and Taiwan should also be gradually lifted.\n" +
                "\n" +
                "Residents of Andorra, Monaco, San Marino and the Vatican should be considered as EU residents for the purpose of this recommendation.\n" +
                "\n" +
                "The criteria to determine the third countries for which the current travel restriction should be lifted were updated on 20 May 2021. They cover the epidemiological situation and overall response to COVID-19, as well as the reliability of the available information and data sources. Reciprocity should also be taken into account on a case by case basis.\n" +
                "\n" +
                "Schengen associated countries (Iceland, Lichtenstein, Norway, Switzerland) also take part in this recommendation.");
        travelRestrictionData.put("Singapore", "Advisory for Singaporean students studying overseas can be found on the MOE website " +
                "and the Travel Advisory on the MOH website. " +
                "Common concerns pertaining to the advisory have been addressed by MOE. MOH Press Release of 21 August 2020 includes " +
                "information on travel for " +
                "studies for students pursuing academic qualification overseas. FAQs on the MOH website (under Border Measures) also " +
                "provides information for students on overseas studies.\n" +
                "Updates on Singapore’s border control measures in response to COVID-19 can be found on the Safetravel website. " +
                "Information on the SG Arrival Card with Electronic Health Declaration can be found on ICA's website. \n" +
                "Singapore has implemented special travel arrangements with some countries/regions, such as China and Malaysia, " +
                "to facilitate travel. Please refer to the ICA SafeTravel website for details.\n" +
                "Visitors from Brunei Darussalam and New Zealand can apply for an Air Travel Pass (ATP) to enter Singapore. More " +
                "information on the ATP can be found in the CAAS Press Release and on the ICA SafeTravel website.\n" +
                "Health advisory for persons issued with Stay-Home Notice (SHN) in Singapore can be found on the MOH website.\n" +
                "Information on Singapore’s COVID-19 Vaccination Programme can be found on MOH’s website on Vaccination.\n" +
                "Transit through Changi Airport is allowed for specific travel routes. Travellers who wish to transit through Singapore " +
                "should check directly with the airlines if their transit route is approved. More information can be found in the " +
                "CAAS Press Release and the Changi Airport website.");
        travelRestrictionData.put("Thailand", "The Tourism Authority of Thailand has released details of the Centre of Economic Situation" +
                " Administration (CESA) chaired by Prime Minister General Prayut Chan-o-cha, a four-stage roadmap to reopen. " +
                "The announcement by the CCSA reveals their plan for six major tourist provinces – Phuket, Krabi, Phang Nga, Surat Thani (Ko Samui), " +
                "Chon Buri (Pattaya), and Chiang Mai – for vaccinated travellers. The tourism sector " +
                "has taken a major financial hit with the COVID pandemic and current travel advisory for domestic travel. " +
                "Thai nationals are encouraged not to travel during this current third wave. " +
                "Airline flight schedules have been reduced as a result. " +
                "Please check your airline site if you have domestic flights scheduled.");
    }

    public TravelRestrictions(){
        fillRestrictions();
    }
}
