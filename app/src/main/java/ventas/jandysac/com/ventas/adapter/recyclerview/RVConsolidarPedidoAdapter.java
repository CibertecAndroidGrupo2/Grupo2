package ventas.jandysac.com.ventas.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.ConsolidarPedidoActivity;
import ventas.jandysac.com.ventas.R;
import ventas.jandysac.com.ventas.entities.ConsolidarPedido;

/**
 * Created by GaryV on 24/09/2015.
 */
public class RVConsolidarPedidoAdapter extends RecyclerView.Adapter<RVConsolidarPedidoAdapter.RVConsolidarPedidoAdapterViewHolder>{
    private ArrayList<ConsolidarPedido> mLstConsolidarPedido;
    private RVConsolidarPedidoAdapterListener mRVConsolidarPedidoAdapterListener;

    public RVConsolidarPedidoAdapter(RVConsolidarPedidoAdapterListener rvConsolidarPedidoAdapterListener) {
        mLstConsolidarPedido = new ArrayList<>();
        this.mRVConsolidarPedidoAdapterListener = rvConsolidarPedidoAdapterListener;
    }

    public interface  RVConsolidarPedidoAdapterListener{
        void onSelectedItem(ConsolidarPedido cPerdido, int position);
    }

    @Override
    public RVConsolidarPedidoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVConsolidarPedidoAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consolidar_pedido_items,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(RVConsolidarPedidoAdapterViewHolder rvConsolidarPedidoAdapterViewHolder, int position) {
        ConsolidarPedido cPerdido = mLstConsolidarPedido.get(position);

        rvConsolidarPedidoAdapterViewHolder.tvPedidosNombre.setText(cPerdido.getNombre());
        rvConsolidarPedidoAdapterViewHolder.tvPedidosItems.setText(cPerdido.getItems());
        rvConsolidarPedidoAdapterViewHolder.tvPedidosTotal.setText(String.valueOf(cPerdido.getTotal()));

        rvConsolidarPedidoAdapterViewHolder.itemView.setTag(position);
        rvConsolidarPedidoAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            if (mRVConsolidarPedidoAdapterListener != null) {
                mRVConsolidarPedidoAdapterListener.onSelectedItem(mLstConsolidarPedido.get(position), position);
            }
        }
    };

    @Override
    public int getItemCount() {
        return mLstConsolidarPedido.size();
    }

    static class RVConsolidarPedidoAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvPedidosNombre, tvPedidosItems, tvPedidosTotal;

        public RVConsolidarPedidoAdapterViewHolder(View itemView) {
            super(itemView);
            tvPedidosNombre = (TextView) itemView.findViewById(R.id.tvPedidosNombre);
            tvPedidosItems = (TextView) itemView.findViewById(R.id.tvPedidosItems);
            tvPedidosTotal = (TextView) itemView.findViewById(R.id.tvPedidosTotal);
        }
    }
}
