package mipymex.mcs.com.pruebas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by César Hernández on 12/01/2017.
 */
public class FotosAdapter extends RecyclerView.Adapter<FotosAdapter.FotosViewHolder>{
    private FotosRespuesta fotosRespuesta;

    @Override
    public FotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto, parent, false);
        return new FotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FotosViewHolder holder, int position) {
        holder.txtCategoria.setText(fotosRespuesta.getCat_pa_parentesco().get(position));
    }

    @Override
    public int getItemCount() {
        return fotosRespuesta.getCat_pa_id().size();
    }
    public void addAll(FotosRespuesta fotosRespuesta) {
        this.fotosRespuesta = fotosRespuesta;
    }

    class FotosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCategoria)TextView txtCategoria;
        public FotosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
