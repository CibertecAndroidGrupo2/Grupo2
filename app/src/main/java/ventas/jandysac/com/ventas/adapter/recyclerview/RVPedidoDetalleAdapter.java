package ventas.jandysac.com.ventas.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.R;
import ventas.jandysac.com.ventas.dao.PedidoDAO;
import ventas.jandysac.com.ventas.entities.PedidoDetalle;

/**
 * Created by Rodolfo on 23/09/2015.
 */
public class RVPedidoDetalleAdapter extends RecyclerView.Adapter<RVPedidoDetalleAdapter.RVPedidoDetalleAdapterViewHolder> implements Filterable {
    private String sFilter = "";
    private ArrayList<PedidoDetalle> mLstPedidoDetalle, mLstPedidoDetalleFilter;
    private RVPedidoDetalleAdapterCallBack mRVPedidoDetalleAdapterCallBack;
    //private RVPedidoDetalleAdapterFilter mRVPedidoDetalleAdapterFilter;

    @Override
    public Filter getFilter() {
        return null;
    }


    public interface RVPedidoDetalleAdapterCallBack {
        void onPedidoDetalleClick(PedidoDetalle pedidodetalle);
    }

    public RVPedidoDetalleAdapter(RVPedidoDetalleAdapterCallBack mRVPedidoDetalleAdapterCallBack) {
        this.mRVPedidoDetalleAdapterCallBack = mRVPedidoDetalleAdapterCallBack;
        mLstPedidoDetalleFilter = new ArrayList<>();
        mLstPedidoDetalle = new ArrayList<>();
        mLstPedidoDetalle.addAll(new PedidoDAO().listPedidoDetalle());
        mLstPedidoDetalleFilter.addAll(mLstPedidoDetalle);
    }

    @Override
    public RVPedidoDetalleAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVPedidoDetalleAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detalle_pedido_items, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RVPedidoDetalleAdapterViewHolder rvPedidoDetalleAdapterViewHolder, int i) {
        PedidoDetalle pedidodetalle = mLstPedidoDetalleFilter.get(i);

        rvPedidoDetalleAdapterViewHolder.itemView.setTag(i);
        rvPedidoDetalleAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
        //rvPedidoDetalleAdapterViewHolder.tvCodigo.setText(cliente.getCodigo());
        rvPedidoDetalleAdapterViewHolder.txtDetalleProducto.setText(pedidodetalle.getCodigo_Producto());
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mRVPedidoDetalleAdapterCallBack != null)
                mRVPedidoDetalleAdapterCallBack.onPedidoDetalleClick(mLstPedidoDetalleFilter.get((int) view.getTag()));
        }
    };

    @Override
    public int getItemCount() {
        return mLstPedidoDetalleFilter.size();
    }

    static class RVPedidoDetalleAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo, txtDetalleProducto;

        public RVPedidoDetalleAdapterViewHolder(View itemView) {
            super(itemView);
            //tvCodigo = (TextView) itemView.findViewById(R.id.txtCodigo);
            txtDetalleProducto = (TextView) itemView.findViewById(R.id.txtDetalleProducto);
        }
    }
}
