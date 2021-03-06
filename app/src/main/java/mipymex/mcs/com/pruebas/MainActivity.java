package mipymex.mcs.com.pruebas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtUsuario)TextView txtUsuario;
    @BindView(R.id.txtFecha)TextView txtFecha;
    @BindView(R.id.txtNumeroContrato)TextView txtNumeroContrato;
    @BindView(R.id.txtBienvenido)TextView txtBienvenido;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Contrato");
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences(Login.PREFS_NAME, 0);
        String bienvenido = String.format(getString(R.string.welcome_user), preferences.getString("Usuario", ""));
        String user = preferences.getString("Usuario", "Error");
        txtUsuario.setText(user);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        txtFecha.setText(date);
        txtNumeroContrato.setText("1");
        txtBienvenido.setText(bienvenido);

    }
}