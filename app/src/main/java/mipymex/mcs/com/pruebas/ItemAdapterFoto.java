package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapterFoto extends BaseAdapter {

    private Context context;
    private ArrayList<ItemTipoFoto> items;

    public ItemAdapterFoto(Context context, ArrayList<ItemTipoFoto> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View rowView = view;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_tipo_foto, viewGroup, false);
        }

        ImageView check_photo = (ImageView) rowView.findViewById(R.id.imgCheckPhoto);
        TextView tipoFoto  = (TextView) rowView.findViewById(R.id.lblTipoFoto);

        ItemTipoFoto itemTipoFoto = this.items.get(position);

        if(CatalogoImagenes.arrayListFotoTomada.contains(itemTipoFoto.getCat_cm_descripcion()))
          check_photo.setImageResource(R.drawable.check_photo);
        else
          check_photo.setImageResource(R.drawable.uncheck_photo);

        tipoFoto.setText(itemTipoFoto.getCat_cm_descripcion());
        return rowView;
    }
}
