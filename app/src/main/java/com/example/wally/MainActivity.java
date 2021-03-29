package com.example.wally;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private Fragment j = new Joystick();
    private Fragment s = new Stream();
    private Fragment set = new Setting();
    private Fragment m = new VoiceManage();
    private BottomNavigationView bottonnav;

    private String _adress;
    private int _port = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottonnav = findViewById(R.id.bottomNavigationView);
        bottonnav.setOnNavigationItemSelectedListener(navListner);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.joystick:
                            selectedFragment = j;
                            break;
                        case R.id.stream:
                            selectedFragment = s;
                            break;
                        case R.id.setting:
                            selectedFragment = set;
                            break;
                        case R.id.voice:
                            selectedFragment = m;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, selectedFragment).commit();
                    return true;
                }
            };

    public void portAndAdress(String adress, int port){
        this._adress = adress;
        this._port = port;
    }

    public void giveCommand(String command){
        if(this._adress != "" && this._port != -1) {
            Client c = new Client(command, this._adress, this._port);
            c.start();
        }
    }

}