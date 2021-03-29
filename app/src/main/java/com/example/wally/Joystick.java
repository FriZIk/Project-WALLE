package com.example.wally;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class Joystick extends Fragment implements SeekBar.OnSeekBarChangeListener, OnCheckedChangeListener
{

    private SeekBar rh;
    private SeekBar lh;
    private SeekBar hh;
    private SeekBar eh;

    private TextView rt;
    private TextView lt;
    private TextView ht;
    private TextView et;

    private ToggleButton rht;
    private ToggleButton lht;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joystick,
                container, false);

        rh = view.findViewById(R.id.righthand);
        lh = view.findViewById(R.id.lefthand);
        hh = view.findViewById(R.id.headrotate);
        eh = view.findViewById(R.id.eye);

        rt = view.findViewById(R.id.rhg);
        lt = view.findViewById(R.id.lhg);
        ht = view.findViewById(R.id.hrg);
        et = view.findViewById(R.id.erg);

        lht = view.findViewById(R.id.lht);
        rht = view.findViewById(R.id.rht);

        rh.setOnSeekBarChangeListener(this);
        lh.setOnSeekBarChangeListener(this);
        hh.setOnSeekBarChangeListener(this);
        eh.setOnSeekBarChangeListener(this);

        JoystickView joystick = (JoystickView) view.findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int a, int s) {
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
                    ((MainActivity) activity).giveCommand("J" + " " + Integer.toString(a) + " " +  Integer.toString(s));
                }
            }
        });
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String command = "";
        switch (seekBar.getId()){
            case R.id.righthand:
                rt.setText(String.valueOf(seekBar.getProgress()));
                command = "R " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.lefthand:
                lt.setText(String.valueOf(seekBar.getProgress()));
                command = "L " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.headrotate:
                ht.setText(String.valueOf(seekBar.getProgress()));
                command = "H " + String.valueOf(seekBar.getProgress());
                break;
            case R.id.eye:
                et.setText(String.valueOf(seekBar.getProgress()));
                command = "E " + String.valueOf(seekBar.getProgress());
                break;
        }
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).giveCommand(command);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String command = "";
        if (isChecked){
         switch (buttonView.getId()) {
            case R.id.lht:
                command = "T 1";
                break;
            case R.id.rht:
                command = "t 1";
                break;
            }
        }
        else{
            switch (buttonView.getId()) {
                case R.id.lht:
                    command = "T 1";
                    break;
                case R.id.rht:
                    command = "t 1";
                    break;
            }
        }
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).giveCommand(command);
        }
    }
}
