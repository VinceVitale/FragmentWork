package com.vincevitale.fragmentwork;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentList.OnURLSelectedListener {

    boolean webPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentList listFragment = new FragmentList();
            ft.add(R.id.fragment_list_container, listFragment);
            ft.commit();
        }

        if(findViewById(R.id.fragment_web_container) != null){
            webPage = true;
            getFragmentManager().popBackStack();

            FragmentWebView webFragment = (FragmentWebView) getFragmentManager().findFragmentById(R.id.fragment_web_container);

            if(webFragment == null){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                webFragment = new FragmentWebView();
                ft.replace(R.id.fragment_web_container, webFragment);
                ft.commit();
            }
        }
    }

    @Override
    public void onURLSelected(String URL){

        if(webPage){
            FragmentWebView webFragment = (FragmentWebView) getFragmentManager().findFragmentById(R.id.fragment_web_container);
            webFragment.updateURLContent(URL);
        }else{
            FragmentWebView webFragment = new FragmentWebView();
            webFragment.setURLContent(URL);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_web_container, webFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    // FragmentOne Button
    public void FragmentOneClick(View view){
        Fragment fragment;
        fragment = new FragmentOne();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // FragmentTwo Button
    public void FragmentTwoClick(View view){
        Fragment fragment;
        fragment = new FragmentTwo();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
