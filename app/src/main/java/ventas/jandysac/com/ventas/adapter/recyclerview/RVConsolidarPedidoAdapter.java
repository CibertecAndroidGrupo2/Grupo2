package ventas.jandysac.com.ventas.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GaryV on 24/09/2015.
 */
public class RVConsolidarPedidoAdapter extends RecyclerView.Adapter<RVConsolidarPedidoAdapter.RVConsolidarPedidoAdapterViewHolder>{
    @Override
    public RVConsolidarPedidoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RVConsolidarPedidoAdapterViewHolder rvConsolidarPedidoAdapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class RVConsolidarPedidoAdapterViewHolder extends RecyclerView.ViewHolder{
        public RVConsolidarPedidoAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
