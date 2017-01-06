package mipymex.mcs.com.pruebas;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        final TabHost host = (TabHost) findViewById(android.R.id.tabhost);

        viewPager  = (ViewPager)    findViewById(R.id.view_pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                host.setCurrentTab(position);
            }
            @Override
            public void onPageScrolled(
                    int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectItem = host.getCurrentTab();
                viewPager.setCurrentItem(selectItem);
            }
        });



        host.addTab(host.newTabSpec("tab1").setIndicator("Solicitante").setContent(
                new Intent(this, InformacionSolicitante.class)));

        host.addTab(host.newTabSpec("tab2").setIndicator("Laboral").setContent(
                new Intent(this, InformacionLaboral.class)));

        host.addTab(host.newTabSpec("tab3").setIndicator("Conyuge").setContent(
                new Intent(this, DatosConyugeHijos.class)));

        host.addTab(host.newTabSpec("tab4").setIndicator("Referencias").setContent(
                new Intent(this, ReferenciasPersonales.class)));

        host.addTab(host.newTabSpec("tab5").setIndicator("Referencias F").setContent(
                new Intent(this, ReferenciasPersonalesDistintoDomicilio.class)));

        host.addTab(host.newTabSpec("tab6").setIndicator("Firma").setContent(
                new Intent(this, CaptureSignature.class)));

        host.addTab(host.newTabSpec("tab7").setIndicator("Imagenes").setContent(
                new Intent(this, CatalogoImagenes.class)));

    }

    public class FakeContent implements TabHost.TabContentFactory{

        Context context;

    public FakeContent(Context context){
        this.context = context;
    }
    @Override
    public View createTabContent(String tag) {
        View fakeView = new View(context);
        fakeView.setMinimumWidth(0);
        fakeView.setMinimumHeight(0);
        return fakeView;
    }
}

}




