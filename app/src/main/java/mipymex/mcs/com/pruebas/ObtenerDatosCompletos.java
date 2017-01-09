package mipymex.mcs.com.pruebas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ObtenerDatosCompletos extends AppCompatActivity  {

    private Connection connection;
    public static int tamDatos;
    private Button btnSincronizar;
    private List datosSolicitante, datosLaborales, datosConyuge, referencias, referencasP;
    private String strSend1, strSend2, strSend3, strSend4, strSend5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sincronizar_datos);

        datosSolicitante = new ArrayList();
        datosLaborales = new ArrayList();
        datosConyuge= new ArrayList();
        referencasP = new ArrayList();
        referencasP = new ArrayList();

        InformacionSolicitante ifo = new InformacionSolicitante();
        ifo.mostraDatos();
        if (ifo.datosSolicitanteLista != null) {

            System.out.println("==========================================" + datosSolicitante.size());
            for (int n = 0; n< datosSolicitante.size(); n++) {
                System.out.println(datosSolicitante.get(n));
            }
        } else {
            System.out.println("==================================== .i. ");
        }


        btnSincronizar = (Button) findViewById(R.id.btnSincroinizar);
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //guardarBaseTemporal();
                construirUrl();
               // sincronizar_datos();
            }
        });


    }

    public void sincronizar_datos(){

       // if(connection.getConnection("No informar")) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setCancelable(true);
            builder1.setTitle("ATENCIÓN");
            builder1.setMessage("¿Esta seguro de enviar los datos?");


            builder1.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {



                       // String cadenaCompleta = strSend1+ strSend2 + strSend3+  strSend4+ strSend5+ strSend6;
                       // new GetWebServices(Catalogo.this).execute(strSend, "enviar", null, null,Service_sincronizar.items.get(i).getPr_cf_credito());

                    //Toast.makeText(Catalogo.this,"Datos enviados",Toast.LENGTH_SHORT).show();
                }
            });
            builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            Button bp = alert11.getButton(DialogInterface.BUTTON_POSITIVE);
            Button bn = alert11.getButton(DialogInterface.BUTTON_NEGATIVE);
            bp.setTextColor(Color.GRAY);
            bn.setTextColor(Color.GRAY);
        //}
    }

    public String construirUrl() {

        for (int h = 0; h <= datosSolicitante.size(); h++) {
            try {
                strSend1 = "&pr_so_apaterno=" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_amaterno=" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_nombre" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_dte_nacimiento" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_lugar" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_edad" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_sexo" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_rfc" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_curp" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_ine" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_edo_civil" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_conyuge_trabaja" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_ingreso_conyuge" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_dependientes" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_numdependientes" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_calle" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_num_ext" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_num_int" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_colonia" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_cp" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_municipio" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_estado" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_tipo_recidencia" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_tiempo_recidencia_a" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_tiempo_recidencia_m" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_credito_vi" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_pago_vivienda" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_tel_casa" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_tel_cel" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_correo" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_cargo_p_publico" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&&pr_so_cargo_publico" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_conyuge_p_publico" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8") +
                        "&pr_so_conyuge_publico" + URLEncoder.encode(String.valueOf(datosSolicitante.get(h)), "UTF-8");

                        System.out.println(strSend1);
                 } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            for (int i = 0; i <= datosLaborales.size(); i++) {
                try {
                    strSend2 = "&pr_so_nom_empresa" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_imss" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_calle_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_num_ext_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_num_int_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_colonia_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_municipio_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_estado_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_departamento_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_puesto_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_ingreso_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_otros_ing_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_otro_ing_c_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_ing_com" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_paga_cred_ins" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_paga_importe_ins" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_paga_ins" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_nombre_jefe" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_antiguedad_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_tel_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_extension_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_fax_emp" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8") +
                        "&pr_so_periodicidad_cobro" + URLEncoder.encode(String.valueOf(datosLaborales.get(i)), "UTF-8");

                    System.out.println(strSend2);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

                for (int j = 0; j <= datosConyuge.size(); j++) {
                    try {
                        strSend3 = "&pr_so_nombre1_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_edad1_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_parentesco1_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_tel1_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_cel1_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_nombre2_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_edad2_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_parentesco2_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_tel2_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_cel2_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_nombre3_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_edad3_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_parentesco3_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_tel3_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8") +
                                "&pr_so_cel3_cony" + URLEncoder.encode(String.valueOf(datosSolicitante.get(j)), "UTF-8");

                        System.out.println(strSend3);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }

                    for (int k = 0; k <= referencias.size(); k++) {
                        try {
                            strSend4 =  "&pr_so_apaterno_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_amaterno_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_nombre_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_calle_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_num_ext_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_num_int_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_colonia_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_cp_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_municipio_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_estado_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_tel_casa_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_tel_cel_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8") +
                                    "&pr_so_correo_ref" + URLEncoder.encode(String.valueOf(datosSolicitante.get(k)), "UTF-8");

                                    System.out.println(strSend4);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                    for (int l = 0; l <= referencasP.size(); l++) {
                        try {
                            strSend5 =  "&pr_so_apaterno_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_amaterno_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_nombre_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_calle_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_num_ext_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_num_int_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_colonia_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_cp_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_municipio_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_estado_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_tel_casa_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_tel_cel_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_correo_ref_p" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8") +
                                    "&pr_so_folio" + URLEncoder.encode(String.valueOf(datosSolicitante.get(l)), "UTF-8");

                                    System.out.println(strSend5);
                        } catch (UnsupportedEncodingException e) {
                             e.printStackTrace();
                        }

                        String cadenaCompleta =Login.IPpublic + "RecibeDatos?v_cliente=2" + strSend1+ strSend2 + strSend3+  strSend4+ strSend5;
                        System.out.println(cadenaCompleta);
             new GetWebServices(ObtenerDatosCompletos.this).execute(cadenaCompleta, "enviar", null, null, null);
        }

        return Login.IPpublic;
    }

}
