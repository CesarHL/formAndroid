package mipymex.mcs.com.pruebas;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;




/**
 * Created by Erick on 02/01/2017.
 */

@SuppressWarnings("deprecation")
public class VentanaPrincipal extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        TabHost host = (TabHost) findViewById(android.R.id.tabhost);


        host.addTab(host.newTabSpec("tab1").setIndicator("Solicitante").setContent(
                new Intent(this, InformacionSolicitante.class)));

        host.addTab(host.newTabSpec("tab2").setIndicator("Laboral").setContent(
                new Intent(this, InformacionLaboral.class)));

        host.addTab(host.newTabSpec("tab3").setIndicator("Conyuge").setContent(
                new Intent(this, DatosConyugeHijos.class)));

        host.addTab(host.newTabSpec("tab4").setIndicator("Referencias").setContent(
                new Intent(this, ReferenciaPersonalFamiliar.class)));

        host.addTab(host.newTabSpec("tab5").setIndicator("Referencias F").setContent(
                new Intent(this, ReferenciasPersonalesDistintoDomicilio.class)));

        host.addTab(host.newTabSpec("tab6").setIndicator("Firma").setContent(
                new Intent(this, CaptureSignature.class)));

        //tab7 catalogo imagenes

    }

}


