package com.galang.penampilkodesumberwebsite;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    ConnectInternetTask c1;
    static TextView mytext;
    static MaterialEditText alamat;
    private Spinner spinner1;
    String protocol="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytext=(TextView)findViewById(R.id.result_text);
        alamat=(MaterialEditText)findViewById(R.id.edt_website);
        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());


    }
    //cek koneksi
    public boolean isOnline()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo=cm.getActiveNetworkInfo();
        if(netinfo!= null && netinfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinner1.getSelectedItem());
        //Memilih spiner yang di klik
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "Kamu memilih Protocol " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                //memasukan spiner yang di klik ke dalam variabel protocol
                protocol=parent.getItemAtPosition(pos).toString();            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    public void dapatkansumber(View view) {
        if(isOnline()==true)
        {  if (protocol.toString().equals("")&&alamat.getText().toString().equals("")) //mengecek apakah user telah memilih protocol dan memasukan alamat
            {
                Toast.makeText(this,"Pilih Protocol dan Masukan Alamat !!!",Toast.LENGTH_SHORT).show();
            }
            else if(alamat.getText().toString().equals(""))//mengecek apakah alamat belum dimasukan
            {
                Toast.makeText(this,"masukan Alamat Websitenya !!!",Toast.LENGTH_SHORT).show();
            }
            else if(protocol.toString().equals(""))//mengecek apakah protocol sudah di klik
            {
                Toast.makeText(this,"Pilih Protocolnya !!!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                c1=new ConnectInternetTask(this);

                c1.execute(protocol+alamat.getText().toString());
            }

        }
        else
        {
            Toast.makeText(this,"Cek Koneksi Anda !!!",Toast.LENGTH_SHORT).show();
        }
    }

    }
