package mipymex.mcs.com.pruebas;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import mipymex.mcs.com.pruebas.fragments.CaptureSignature;
import mipymex.mcs.com.pruebas.fragments.CatalogoImagenes;
import mipymex.mcs.com.pruebas.fragments.DatosConyugeHijos;
import mipymex.mcs.com.pruebas.fragments.InformacionLaboral;
import mipymex.mcs.com.pruebas.fragments.InformacionSolicitante;
import mipymex.mcs.com.pruebas.fragments.ReferenciasPersonales;
import mipymex.mcs.com.pruebas.fragments.ReferenciasPersonalesDistintoDomicilio;


/**
 * Created by Erick on 02/01/2017.
 */


public class VentanaPrincipal extends AppCompatActivity {

    private  ViewPager viewPager;
    private  TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MiPYMEX");

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new InformacionSolicitante(), "Solicitante");
        adapter.addFragment(new InformacionLaboral(), "Laboral");
        adapter.addFragment(new DatosConyugeHijos(), "Cónyuge");
        adapter.addFragment(new ReferenciasPersonales(), "Referencias");
        adapter.addFragment(new ReferenciasPersonalesDistintoDomicilio(), "Referencias F");
        adapter.addFragment(new CaptureSignature(), "Firma");
        adapter.addFragment(new CatalogoImagenes(), "Imágenes");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }


}




