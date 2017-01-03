package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataDB.TABLE_NAME_USUARIOS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataDB.NAME + " TEXT, " +
                DataDB.PASSWORD + " TEXT);"
        );

        Log.i(DataDB.TABLE_NAME_USUARIOS," TABLA USUARIOS CREADA");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataDB.TABLE_NAME_SOLICITUD + "( " +
                "_id INTEGER PRIMARY KEY, " +
                DataDB.PR_SO_NUMSOLICITUD + " TEXT, " +
                DataDB.PR_SO_MTO_PRESTAMO + " TEXT, " +
                DataDB.PR_SO_PLAZO + " TEXT, " +
                DataDB.PR_SO_ASESOR + " TEXT, " +
                DataDB.PR_SO_DTE_SOLICITUD + " TEXT, " +
                DataDB.PR_SO_DESTINO + " TEXT, " +

                DataDB.PR_SO_APATERNO + " TEXT, " +
                DataDB.PR_SO_AMATERNO + " TEXT, " +
                DataDB.PR_SO_NOMBRE + " TEXT, " +
                DataDB.PR_SO_DTE_NACIMIENTO + " TEXT, " +
                DataDB.PR_SO_LUGAR + " TEXT, " +
                DataDB.PR_SO_EDAD + " TEXT, " +
                DataDB.PR_SO_SEXO + " TEXT, " +
                DataDB.PR_SO_RFC + " TEXT, " +
                DataDB.PR_SO_CURP + " TEXT, " +
                DataDB.PR_SO_INE + " TEXT, " +
                DataDB.PR_SO_EDO_CIVIL + " TEXT, " +
                DataDB.PR_SO_CONYUGE_TRABAJA + " TEXT, " +
                DataDB.PR_SO_INGRESO_CONYUGE + " TEXT, " +
                DataDB.PR_SO_DEPENDIENTES + " TEXT, " +
                DataDB.PR_SO_NUMDEPENDIENTES + " TEXT, " +
                DataDB.PR_SO_CALLE + " TEXT, " +
                DataDB.PR_SO_NUM_EXT + " TEXT, " +
                DataDB.PR_SO_NUM_INT + " TEXT, " +
                DataDB.PR_SO_COLONIA + " TEXT, " +
                DataDB.PR_SO_CP + " TEXT, " +
                DataDB.PR_SO_MUNICIPIO + " TEXT, " +
                DataDB.PR_SO_ESTADO + " TEXT, " +
                DataDB.PR_SO_TIPO_RECIDENCIA + " TEXT, " +
                DataDB.PR_SO_TIEMPO_RESIDENCIA_A + " TEXT, " +
                DataDB.PR_SO_TIEMPO_RESIDENCIA_M+ " TEXT, " +
                DataDB.PR_SO_CREDITO_VI + " TEXT, " +
                DataDB.PR_SO_PAGO_VIVIENDA + " TEXT, " +
                DataDB.PR_SO_TEL_CASA + " TEXT, " +
                DataDB.PR_SO_TEL_CEL + " TEXT, " +
                DataDB.PR_SO_CORREO + " TEXT, " +
                DataDB.PR_SO_CARGO_P_PUBLICO + " TEXT, " +
                DataDB.PR_SO_CARGO_PUBLICO + " TEXT, " +
                DataDB.PR_SO_CONYUGE_P_PUBLICO + " TEXT, " +
                DataDB.PR_SO_CONYUGE_PUBLICO + " TEXT, " +

                DataDB.PR_SO_NOM_EMPRESA + " TEXT, " +
                DataDB.PR_SO_IMSS + " TEXT, " +
                DataDB.PR_SO_CALLE_EMP + " TEXT, " +
                DataDB.PR_SO_NUM_EXT_EMP + " TEXT, " +
                DataDB.PR_SO_NUM_INT_EMP + " TEXT, " +
                DataDB.PR_SO_COLONIA_EMP + " TEXT, " +
                DataDB.PR_SO_CP_EMP + " TEXT, " +
                DataDB.PR_SO_MUNICIPIO_EMP + " TEXT, " +
                DataDB.PR_SO_ESTADO_EMP + " TEXT, " +
                DataDB.PR_SO_DEPARTAMENTO_EMP + " TEXT, " +
                DataDB.PR_SO_PUESTO_EMP + " TEXT, " +
                DataDB.PR_SO_INGRESO_EMP + " TEXT, " +
                DataDB.PR_SO_OTROS_ING_EMP + " TEXT, " +
                DataDB.PR_SO_OTRO_ING_C_EMP + " TEXT, " +
                DataDB.PR_SO_ING_COM + " TEXT, " +
                DataDB.PR_SO_PAGA_CRED_INS + " TEXT, " +
                DataDB.PR_SO_PAGA_IMPORTE_INS + " TEXT, " +
                DataDB.PR_SO_PAGA_INS + " TEXT, " +
                DataDB.PR_SO_NOMBRE_JEFE + " TEXT, " +
                DataDB.PR_SO_ANTIGUEDAD_EMP + " TEXT, " +
                DataDB.PR_SO_TEL_EMP + " TEXT, " +
                DataDB.PR_SO_EXTENSION_EMP + " TEXT, " +
                DataDB.PR_SO_FAX_EMP + " TEXT, " +
                DataDB.PR_SO_PERIODICIDAD_COBRO + " TEXT, " +

                DataDB.PR_SO_NOMBRE1_CONY + " TEXT, " +
                DataDB.PR_SO_EDAD1_CONY + " TEXT, " +
                DataDB.PR_SO_PARENTESCO1_CONY + " TEXT, " +
                DataDB.PR_SO_TEL1_CONY + " TEXT, " +
                DataDB.PR_SO_CEL1_CONY + " TEXT, " +
                DataDB.PR_SO_NOMBRE2_CONY + " TEXT, " +
                DataDB.PR_SO_EDAD2_CONY + " TEXT, " +
                DataDB.PR_SO_PARENTESCO2_CONY + " TEXT, " +
                DataDB.PR_SO_TEL2_CONY + " TEXT, " +
                DataDB.PR_SO_CEL2_CONY + " TEXT, " +
                DataDB.PR_SO_NOMBRE3_CONY + " TEXT, " +
                DataDB.PR_SO_EDAD3_CONY + " TEXT, " +
                DataDB.PR_SO_PARENTESCO3_CONY + " TEXT, " +
                DataDB.PR_SO_TEL3_CONY + " TEXT, " +
                DataDB.PR_SO_CEL3_CONY + " TEXT, " +

                DataDB.PR_SO_APATERNO_REF + " TEXT, " +
                DataDB.PR_SO_AMATERNO_REF + " TEXT, " +
                DataDB.PR_SO_NOMBRE_REF + " TEXT, " +
                DataDB.PR_SO_CALLE_REF + " TEXT, " +
                DataDB.PR_SO_NUM_EXT_REF + " TEXT, " +
                DataDB.PR_SO_NUM_INT_REF + " TEXT, " +
                DataDB.PR_SO_COLONIA_REF + " TEXT, " +
                DataDB.PR_SO_CP_REF + " TEXT, " +
                DataDB.PR_SO_MUNICIPIO_REF + " TEXT, " +
                DataDB.PR_SO_ESTADO_REF + " TEXT, " +
                DataDB.PR_SO_TEL_CASA_REF + " TEXT, " +
                DataDB.PR_SO_TEL_CEL_REF + " TEXT, " +
                DataDB.PR_SO_CORREO_REF + " TEXT, " +

                DataDB.PR_SO_APATERNO_REF_P + " TEXT, " +
                DataDB.PR_SO_AMATERNO_REF_P + " TEXT, " +
                DataDB.PR_SO_NOMBRE_REF_P + " TEXT, " +
                DataDB.PR_SO_CALLE_REF_P + " TEXT, " +
                DataDB.PR_SO_NUM_EXT_REF_P + " TEXT, " +
                DataDB.PR_SO_NUM_INT_REF_P + " TEXT, " +
                DataDB.PR_SO_COLONIA_REF_P + " TEXT, " +
                DataDB.PR_SO_CP_REF_P + " TEXT, " +
                DataDB.PR_SO_MUNICIPIO_REF_P + " TEXT, " +
                DataDB.PR_SO_ESTADO_REF_P + " TEXT, " +
                DataDB.PR_SO_TEL_CASA_REF_P + " TEXT, " +
                DataDB.PR_SO_TEL_CEL_REF_P + " TEXT, " +
                DataDB.PR_SO_CORREO_REF_P + " TEXT, " +

                DataDB.PR_SO_FOLIO + " TEXT);"
        );

        Log.i(DataDB.TABLE_NAME_SOLICITUD," TABLA SOLICITUD CREADA");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}