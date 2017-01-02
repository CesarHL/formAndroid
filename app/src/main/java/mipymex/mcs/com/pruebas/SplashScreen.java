package mipymex.mcs.com.pruebas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Erick on 02/01/2017.
 */

public class SplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash_screen);

            Thread thread = new Thread(){
                @Override
                public void run() {
                    try{
                        sleep(3000);
                        Intent intent = new Intent(getApplicationContext(), VentanaPrincipal.class);
                        startActivity(intent);
                        finish();

                    } catch(InterruptedException ex){
                        ex.printStackTrace();

                    }
                }
            };
            thread.start();
        }
}
