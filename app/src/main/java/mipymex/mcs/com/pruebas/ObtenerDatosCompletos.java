package mipymex.mcs.com.pruebas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ObtenerDatosCompletos extends AppCompatActivity  {

    private SQLiteDatabase db = null;
    private Cursor c = null;
    private Connection connection;
    public static int tamDatos;
    public static List<Item> items = null;
    private String aux;


   /* public void sincronizar_datos(){

        if(connection.getConnection("No informar")) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(Catalogo.this);
            builder1.setCancelable(true);
            builder1.setTitle("ATENCIÓN");
            builder1.setMessage("¿Esta seguro de enviar los datos?");


            builder1.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                  //  sincronizar_fotos();
                   for (int i = 0; i < Service_sincronizar.items.size(); i++) {
                        String strSend = null;
                        try {
                            strSend = Login.IPpublic + "RecibeDatos?v_cliente=2" +
                                    "&pr_so_apaterno=" + Service_sincronizar.items.get(i).getLogin_usuario() +
                                    "&pr_so_amaterno=" + Service_sincronizar.items.get(i).getNum_agencia() +
                                    "&pr_so_nombre" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_dte_nacimiento" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_lugar" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_edad + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_sexo" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_rfc" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_curp" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_ine + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_edo_civil" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_conyuge_trabaja" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_ingreso_conyuge" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_dependientes + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_numdependientes" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_calle" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_num_ext" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_num_int + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_colonia" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_cp" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_municipio" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_estado + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_tipo_recidencia" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_tiempo_recidencia_a" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_tiempo_recidencia_m" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_credito_vi + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_pago_vivienda" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_tel_casa" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_tel_cel" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_correo + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_cargo_p_publico" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&&pr_so_cargo_publico" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&pr_so_conyuge_p_publico" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&pr_so_conyuge_publico + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&pr_so_amaterno" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +

                                    "&pr_so_amaterno" + URLEncoder.encode(Service_sincronizar.items.get(i).getTmp_id(), "UTF-8");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        new GetWebServices(Catalogo.this).execute(strSend, "enviar", null, null,Service_sincronizar.items.get(i).getPr_cf_credito());
                    }
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
        }
    }*/

}
