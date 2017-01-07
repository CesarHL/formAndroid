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

public class ObtenerDatosCompletos extends Fragment {

    private SQLiteDatabase db = null;
    private Cursor c = null;
    public static int tamDatos;

    public void datosSincronizar() {
        db = getActivity().openOrCreateDatabase(DataDB.DB_NAME, android.content.Context.MODE_PRIVATE, null);
        try {
            String[] valores_recuperar = {"_id",
                    DataDB.PR_SO_APATERNO,
                    DataDB.PR_SO_AMATERNO,
                    DataDB.PR_SO_NOMBRE,
                    DataDB.PR_SO_DTE_NACIMIENTO,
                    DataDB.PR_SO_LUGAR,
                    DataDB.PR_SO_EDAD,
                    DataDB.PR_SO_SEXO,
                    DataDB.PR_SO_RFC,
                    DataDB.PR_SO_CURP,
                    DataDB.PR_SO_INE,
                    DataDB.PR_SO_EDO_CIVIL,
                    DataDB.PR_SO_CONYUGE_TRABAJA,
                    DataDB.PR_SO_INGRESO_CONYUGE,
                    DataDB.PR_SO_DEPENDIENTES,
                    DataDB.PR_SO_NUMDEPENDIENTES,
                    DataDB.PR_SO_CALLE,
                    DataDB.PR_SO_NUM_EXT,
                    DataDB.PR_SO_NUM_INT,
                    DataDB.PR_SO_COLONIA,
                    DataDB.PR_SO_CP,
                    DataDB.PR_SO_MUNICIPIO,
                    DataDB.PR_SO_ESTADO,
                    DataDB.PR_SO_TIPO_RECIDENCIA,
                    DataDB.PR_SO_TIEMPO_RESIDENCIA_A,
                    DataDB.PR_SO_TIEMPO_RESIDENCIA_M,
                    DataDB.PR_SO_CREDITO_VI,
                    DataDB.PR_SO_PAGO_VIVIENDA,
                    DataDB.PR_SO_TEL_CASA,
                    DataDB.PR_SO_TEL_CEL,
                    DataDB.PR_SO_CORREO,
                    DataDB.PR_SO_CARGO_P_PUBLICO,
                    DataDB.PR_SO_CARGO_PUBLICO,
                    DataDB.PR_SO_CONYUGE_P_PUBLICO,
                    DataDB.PR_SO_CONYUGE_PUBLICO
            };
            Cursor c = db.query(DataDB.TABLE_NAME_INFO_SOLICITANTE, valores_recuperar,null,null,null,null,DataDB.PR_SO_APATERNO,null);
            tamDatos = c.getCount();
            getActivity().setTitle("Sincronizar: " + tamDatos); // Cambiar el titulo de la pantalla
            if (c.moveToFirst()) {
                do {
                    System.out.println("id:" + c.getString(0) + "\nApellidoPaterno" + c.getString(1) + "\nAMaterno" + c.getString(2) + "\nNombre" + c.getString(3)+ "\n" +
                            c.getString(4) + "\n" + c.getString(5) + "\n" + c.getString(6) + "\n" + c.getString(7));

                  /*  items.add(new Item(c.getString(1), c.getString(2), Integer.parseInt(c.getString(3)),c.getString(4), c.getString(5), c.getString(6),
                            c.getString(7), c.getString(8), c.getString(9),c.getString(10), c.getString(11), c.getString(12),c.getString(13),c.getString(14),
                            c.getString(15), c.getString(16), c.getString(17),c.getString(18),c.getString(19), c.getString(20),c.getString(21),c.getString(22),c.getString(23),1));
                */
                    db.delete(DataDB.TABLE_NAME_INFO_LABORAL, null, null);
                    System.out.println("Borrado");
                } while (c.moveToNext());
                c.close();
            } else {
                System.out.println("No existen cuentas a sincronizar");
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        } finally {
            db.close();
        }
    }
/*
    public void sincronizar_datos(){

        if(connection.getConnection("No informar")) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(Catalogo.this);
            builder1.setCancelable(true);
            builder1.setTitle("ATENCIÓN");
            builder1.setMessage("¿Esta seguro de enviar los datos?");


            builder1.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {


                    sincronizar_fotos();
                    for (int i = 0; i < Service_sincronizar.items.size(); i++) {
                        String strSend = null;
                        try {
                            strSend = Login.IPpublic + "RecibeDatos?v_cliente=1" +
                                    "&v_usuario=" + Service_sincronizar.items.get(i).getLogin_usuario() +
                                    "&v_num_agencia=" + Service_sincronizar.items.get(i).getNum_agencia() +
                                    "&v_cat_pa_parentesco=" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_pa_parentesco(), "UTF-8") +
                                    "&v_nombre_contacto=" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nom_parentesco(), "UTF-8") +
                                    "&v_cat_co_resultado=" + Service_sincronizar.items.get(i).getCat_co_resultado() +
                                    "&v_cat_co_rdescripcion=" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_co_rdescripcion(), "UTF-8") +
                                    "&v_cat_np_codigo=" + Service_sincronizar.items.get(i).getCat_np_codigo() +
                                    "&v_cat_np_descripcion=" + URLEncoder.encode(Service_sincronizar.items.get(i).getCat_np_descripcion(), "UTF-8") +
                                    "&v_pr_cf_credito=" + Service_sincronizar.items.get(i).getPr_cf_credito() +
                                    "&v_pr_ca_rpu=" + Service_sincronizar.items.get(i).getRpu() +
                                    "&v_pr_ca_medidor=" + Service_sincronizar.items.get(i).getMedidor() +
                                    "&v_pr_cd_latitud=" + Service_sincronizar.items.get(i).getPr_cd_latitud() +
                                    "&v_pr_cd_longitud=" + Service_sincronizar.items.get(i).getPr_cd_longitud() +
                                    "&v_fecha_visita=" + URLEncoder.encode(Service_sincronizar.items.get(i).getFecha(), "UTF-8") +
                                    "&v_comentario=" + URLEncoder.encode(Service_sincronizar.items.get(i).getComentario(), "UTF-8") +
                                    "&v_pregunta1=" + Service_sincronizar.items.get(i).getPr_da_pregunta1() +
                                    "&v_pr_da_nombre=" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_nombre(), "UTF-8") +
                                    "&v_pr_da_cuenta=" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_cuenta(), "UTF-8") +
                                    "&v_pr_da_rpu=" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_rpu(), "UTF-8") +
                                    "&v_pregunta2=" + Service_sincronizar.items.get(i).getPr_da_pregunta2() +
                                    "&v_pr_da_domicilio=" + URLEncoder.encode(Service_sincronizar.items.get(i).getPr_da_domicilio(), "UTF-8") +
                                    "&v_id=" + URLEncoder.encode(Service_sincronizar.items.get(i).getTmp_id(), "UTF-8");

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
/*
    public void sincronizar_fotos(){

        if(connection.getConnection("No informar")) {

            db = openOrCreateDatabase(DataDB.DB_NAME,android.content.Context.MODE_PRIVATE ,null);
            Cursor c = db.rawQuery("SELECT * FROM " + DataDB.TABLE_NAME_IMAGEN, null);
            try {
                String strSendFotos;
                System.out.println("Imagenes encontradas: " + c.getCount());
                if (c.moveToFirst()) {
                    do{
                        strSendFotos = Login.IPpublic + "RecibeImagen" +
                                "?v_cliente=1" +
                                "&v_pr_cf_credito=" + c.getString(0) +
                                "&v_fecha_visita=" + URLEncoder.encode(c.getString(1), "UTF-8") +
                                "&v_tipo=" + c.getString(2) +
                                "&v_id="+ URLEncoder.encode(c.getString(4), "UTF-8") +
                                "&v_imagen=" + URLEncoder.encode(c.getString(3),"UTF-8");

                        new GetWebServicesFotos(Catalogo.this).execute(strSendFotos, "enviar_fotos",c.getString(0),c.getString(2));
                    }while(c.moveToNext());
                } else {
                    System.out.println("No existen imagenes en BD local");
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }finally {
                db.close();
            }
        }
    }
    */
}
