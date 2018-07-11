package com.example.hp.souq2.task2.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.souq2.R;
import com.example.hp.souq2.task2.model.CityInfo;
import com.example.hp.souq2.task2.model.CountryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Bidi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextView textViewTerms, textViewCountryInfo;
    Button btnChangeLang;
    Spinner countryInfoSpinner;
    Spinner countryNameSpinner;
    Spinner cityNameSpinner;
    CountryInfo countryInfoA;
    ArrayList countryInfoArrayList, countryNameArrayList, cityNameArrayList;
    ArrayList<CountryInfo> countryInfoArrayListModel;
    ArrayList<CityInfo> cityInfoArrayListModel;
    ArrayAdapter<String> adapterCountryInfoSpinner, adapterCountryNameSpinner, adapterCityNameSpinner;
    Map countryInfoMap;
    String country;
    String url1, url2;
    String label = "";
    String label2 = "";
    int countryId;
    String[] mArr2 = {};
    String[] mArr = {};
    final FragmentTransaction ft = getFragmentManager().beginTransaction();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setArViews();
        countryInfoArrayListModel = new ArrayList<CountryInfo>();
        cityInfoArrayListModel = new ArrayList<>();
        countryInfoArrayList = new ArrayList();
        countryNameArrayList = new ArrayList();
        cityNameArrayList = new ArrayList();
        countryInfoMap = new HashMap();
        countryInfoA = new CountryInfo();
        if (isOnline()) {
            getData();
            setTextViewTerms();
            setBtnChangeLang();
            setCountryNameSpinner();
            setCityNameSpinner();
            setCountryInfoSpinner();
        }else {
            Toast.makeText(this, "check connection", Toast.LENGTH_SHORT).show();
        }
    }

    void getData() {
        url1 = "http://souq.hardtask.co/app/app.asmx/GetCountries";
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, url1, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int z = 0;
                        try {
                            for (int x = 0; x < response.length(); x++) {
                                z++;
                                JSONObject jsonObject = response.getJSONObject(x);
                                String id = jsonObject.getString("Id");
                                String titleEN = jsonObject.getString("TitleEN");
                                String titleAR = jsonObject.getString("TitleAR");
                                String currencyId = jsonObject.getString("CurrencyId");
                                String currencyEN = jsonObject.getString("CurrencyEN");
                                String currencyAR = jsonObject.getString("CurrencyAR");
                                String codeEN = jsonObject.getString("CodeEN");
                                String codeAR = jsonObject.getString("CodeAR");
                                String code = jsonObject.getString("Code");
                                CountryInfo countryInfo = new CountryInfo(id, titleEN, titleAR, currencyId, currencyEN, currencyAR, codeEN, codeAR, code);
                                countryInfoArrayListModel.add(countryInfo);
                                countryInfoMap.put("Id", id);
                                countryInfoMap.put("TitleEN", titleEN);
                                countryInfoMap.put("TitleAR", titleAR);
                                countryInfoMap.put("CurrencyId", currencyId);
                                countryInfoMap.put("CurrencyEN", currencyEN);
                                countryInfoMap.put("CurrencyAR", currencyAR);
                                countryInfoMap.put("CodeEN", codeEN);
                                countryInfoMap.put("CodeAR", codeAR);
                                countryInfoMap.put("Code", code);

                                countryNameArrayList.add(countryInfoMap.get("TitleEN").toString());

                                countryInfoArrayList.add(countryInfoArrayListModel.get(x));
                                adapterCountryInfoSpinner.notifyDataSetChanged();
                                adapterCountryNameSpinner.notifyDataSetChanged();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(RegisterActivity.this).add(jsonArrayRequest);
    }

    void getData2() {
        url2 = "http://souq.hardtask.co/app/app.asmx/GetCities?countryId=" + countryId;
        JsonArrayRequest jsonArrayRequest2 =
                new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int z = 0;
                        try {
                            for (int x = 0; x < response.length(); x++) {
                                z++;
                                JSONObject jsonObject = response.getJSONObject(x);
                                String id = jsonObject.getString("Id");
                                String titleEN = jsonObject.getString("TitleEN");
                                String titleAR = jsonObject.getString("TitleAR");
                                String countryId = jsonObject.getString("CountryId");
                                CityInfo cityInfo = new CityInfo(id, titleEN, titleAR, countryId);
                                cityInfoArrayListModel.add(cityInfo);
//                                cityNameArrayList.add(cityInfoArrayListModel.get(x).getId());
                                if (textViewTerms.getText().equals("الشروط والاحكام")) {
                                    cityNameArrayList.add(cityInfoArrayListModel.get(x).getTitleAR());
                                } else {
                                    cityNameArrayList.add(cityInfoArrayListModel.get(x).getTitleEN());
                                }
//                                cityNameArrayList.add(cityInfoArrayListModel.get(x).getTitleAR());
//                                cityNameArrayList.add(cityInfoArrayListModel.get(x).getCountryId());
                                adapterCityNameSpinner.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(RegisterActivity.this).add(jsonArrayRequest2);

    }

    void initView() {
        textViewCountryInfo = findViewById(R.id.country_info);
        textViewTerms = findViewById(R.id.txt_terms_conditions);
        countryInfoSpinner = findViewById(R.id.spinner_country_info);
        countryNameSpinner = findViewById(R.id.spinner_country_name);
        cityNameSpinner = findViewById(R.id.spinner_city_name);
        btnChangeLang = findViewById(R.id.btn_change_lang);
    }

    void setTextViewTerms() {
        textViewTerms.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
//                ft.replace(R.id.container, new WebViewFragment(), "findThisFragment2")
//                        .addToBackStack(null)
//                        .commit();

                startActivity(new Intent(getApplicationContext(), WebViewSampleActivity.class));
            }
        });
    }

    void setBtnChangeLang() {
        btnChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLang();

            }
        });

    }

    void setCityNameSpinner() {
        adapterCityNameSpinner = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, cityNameArrayList);
        cityNameSpinner.setAdapter(adapterCityNameSpinner);
        cityNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    void setCountryInfoSpinner() {
        mArr = new String[]{"Choose info", "Id", "TitleEN", "TitleAR", "CurrencyId", "CurrencyEN", "CurrencyAR", "CodeEN", "CodeAR", "Code"};
        mArr2 = new String[]{"", "id", "titleEN", "titleAR", "currencyId", "currencyEN", "currencyAR", "codeEN", "codeAR", "code"};
        adapterCountryInfoSpinner = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, mArr);
        countryInfoSpinner.setAdapter(adapterCountryInfoSpinner);
        countryInfoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    textViewCountryInfo.setText("");
                } else {
                    label = parent.getItemAtPosition(position).toString();
                    label2 = mArr2[position];
                    textViewCountryInfo.setText(countryInfoA.getVariableName(countryInfoA, label2));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void setCountryNameSpinner() {
        adapterCountryNameSpinner = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, countryNameArrayList);
        countryNameSpinner.setAdapter(adapterCountryNameSpinner);
        countryNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryId = parent.getSelectedItemPosition() + 1;
                cityNameArrayList.clear();
                adapterCityNameSpinner.notifyDataSetChanged();
                getData2();
                countryInfoA = countryInfoArrayListModel.get(position);
                if (!label2.equals("") && !label.equals("Choose info")) {
                    textViewCountryInfo.setText(countryInfoA.getVariableName(countryInfoA, label2));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void setArViews() {
        if (textViewTerms.getText().equals("الشروط والاحكام")) {
            countryInfoSpinner.setBackgroundResource(R.drawable.list_small_ar);
            countryInfoSpinner.setLayoutDirection(Bidi.DIRECTION_RIGHT_TO_LEFT);
            countryNameSpinner.setBackgroundResource(R.drawable.list_ar);
            countryNameSpinner.setLayoutDirection(Bidi.DIRECTION_RIGHT_TO_LEFT);
            cityNameSpinner.setBackgroundResource(R.drawable.list_ar);
            cityNameSpinner.setLayoutDirection(Bidi.DIRECTION_RIGHT_TO_LEFT);
        } else {
            countryInfoSpinner.setBackgroundResource(R.drawable.list_small_en);
            countryNameSpinner.setBackgroundResource(R.drawable.list_en);
            cityNameSpinner.setBackgroundResource(R.drawable.list_en);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, RegisterActivity.class);
        startActivity(refresh);
        finish();

    }


    void changeLang() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(RegisterActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(RegisterActivity.this);
        }
        builder.setTitle(R.string.chose_your_language)
                .setPositiveButton(R.string.arabic, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        setLocale("ar");
                    }
                })
                .setNegativeButton(R.string.english, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        setLocale("en");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .show();

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
