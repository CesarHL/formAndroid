package mipymex.mcs.com.pruebas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Erick on 02/01/2017.
 */


public class VentanaPrincipal extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Solicitante"));
        tabLayout.addTab(tabLayout.newTab().setText("Laboral"));
        tabLayout.addTab(tabLayout.newTab().setText("Cónyuge"));
        tabLayout.addTab(tabLayout.newTab().setText("Referencias"));
        tabLayout.addTab(tabLayout.newTab().setText("Referencias F"));
        tabLayout.addTab(tabLayout.newTab().setText("Firma"));
        tabLayout.addTab(tabLayout.newTab().setText("Imágenes"));



//        host.addTab(host.newTabSpec("tab1").setIndicator("Solicitante").setContent(
//                new Intent(this, InformacionSolicitante.class)));
//
//        host.addTab(host.newTabSpec("tab2").setIndicator("Laboral").setContent(
//                new Intent(this, InformacionLaboral.class)));
//
//        host.addTab(host.newTabSpec("tab3").setIndicator("Conyuge").setContent(
//                new Intent(this, DatosConyugeHijos.class)));
//
//        host.addTab(host.newTabSpec("tab4").setIndicator("Referencias").setContent(
//                new Intent(this, ReferenciasPersonales.class)));
//
//        host.addTab(host.newTabSpec("tab5").setIndicator("Referencias F").setContent(
//                new Intent(this, ReferenciasPersonalesDistintoDomicilio.class)));
//
//        host.addTab(host.newTabSpec("tab6").setIndicator("Firma").setContent(
//                new Intent(this, CaptureSignature.class)));
//
//        host.addTab(host.newTabSpec("tab7").setIndicator("Imagenes").setContent(
//                new Intent(this, CatalogoImagenes.class)));

    }

//    public class FakeContent implements TabHost.TabContentFactory{
//
//        Context context;
//
//        public FakeContent(Context context){
//            this.context = context;
//        }
//        @Override
//        public View createTabContent(String tag) {
//            View fakeView = new View(context);
//            fakeView.setMinimumWidth(0);
//            fakeView.setMinimumHeight(0);
//            return fakeView;
//        }
//    }

}




