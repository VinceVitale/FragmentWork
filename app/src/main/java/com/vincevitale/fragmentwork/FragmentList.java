package com.vincevitale.fragmentwork;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends Fragment {

    OnURLSelectedListener mListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.list_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        displayListView();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Activity){
            try {
                mListener = (OnURLSelectedListener) (Activity) context;
            }catch (ClassCastException e){
                throw new ClassCastException(context.toString() + " must implement OnURLSelectedListener");
            }
        }
    }

    public interface OnURLSelectedListener{
        public void onURLSelected(String URL);
    }

    private void displayListView(){

        final List<String> webList = new ArrayList<String>();
        webList.add("University of Central Missouri");
        webList.add("Computer Science - UCM");
        webList.add("CS Faculty - UCM");
        webList.add("Dr. Lui's Web Page - UCM");
        webList.add("Dr. Lui's Web Page - Google Scholar");
        webList.add("Google Search");
        webList.add("Google Mail");
        webList.add("Android Developers Website");

        final List<String> urlList = new ArrayList<String>();
        urlList.add("http://www.ucmo.edu");
        urlList.add("http://www.ucmo.edu/cs");
        urlList.add("https://www.ucmo.edu/cs/faculty.cfm");
        urlList.add("https://www.ucmo.edu/cs-math/facstaff/lui.cfm");
        urlList.add("http://scholar.google.com/citations?user=UIhn-TsAAAAJ&hl=en");
        urlList.add("http://www.google.com");
        urlList.add("http://mail.google.com");
        urlList.add("https://developer.android.com/index.html");

        // Create an ArrayAdapter from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.web_list, webList);
        ListView listView = (ListView) getView().findViewById(R.id.listOfWebsites);

        // Assign Adapter to ListView
        listView.setAdapter(dataAdapter);
        // Enables filtering for the Contents of the given ListView
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // Send the URl to the Host Activity
                mListener.onURLSelected(urlList.get(position));
            }
        });
    }

} // FragmentList class End
