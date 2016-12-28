package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

public class Connection {

    private Context context;

    public Connection(Context context) {

        this.context = context;
    }

    public boolean getConnection(String info) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setCancelable(true);

        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        /*--------------------------------- CONECTADO A INTERNET ---------------------------------*/
        if (activeNetwork != null) {
            /*------------------------------- CONECTADO A WIFI -----------------------------------*/
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                if(info.equals("Informar")) {
                    builder1.setTitle("CONECTADO");
                    builder1.setMessage("Tu Dispositivo tiene Conexion a Wifi");
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    Button bq = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                    bq.setTextColor(Color.GRAY);
                }
                return true;
            }
            /*------------------------------- CONECTADO A DATOS ----------------------------------*/
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (info.equals("Informar")) {
                    builder1.setTitle("CONECTADO");
                    builder1.setMessage("Tu Dispositivo tiene Conexion Movil");
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    Button bq = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
                    bq.setTextColor(Color.GRAY);
                }
                return true;
            }
        }
        /*-------------------------------- NO CONECTADO A INTERNET -------------------------------*/
        else {
            builder1.setTitle("NO CONECTADO");
            builder1.setMessage("Tu Dispositivo no tiene Conexion a Internet");
            AlertDialog alert11 = builder1.create();
            alert11.show();
            Button bq = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
            bq.setTextColor(Color.GRAY);
            return false;
        }
        return false;
    }
}