package com.example.wally;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class Setting extends Fragment implements View.OnClickListener
{
    private Button connect;
    private EditText port;
    private EditText adress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,
                container, false);

        connect = view.findViewById(R.id.connect_but);

        port = view.findViewById(R.id.port);
        adress = view.findViewById(R.id.adress);

        connect.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        //String a = adress.getText().toString();
        int p = Integer.parseInt(port.getText().toString());
        switch (v.getId()){
            case R.id.connect_but:
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
                    ((MainActivity) activity).portAndAdress("192.168.43.84", 2000);
                }
        }
    }
}
