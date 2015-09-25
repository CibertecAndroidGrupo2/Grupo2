package ventas.jandysac.com.ventas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.adapter.recyclerview.RVConsolidarPedidoAdapter;
import ventas.jandysac.com.ventas.entities.ConsolidarPedido;

/**
 * Created by Administrator on 19/09/2015.
 */
public class ConsolidarPedidoActivity extends AppCompatActivity implements RVConsolidarPedidoAdapter.RVConsolidarPedidoAdapterListener {


    private ArrayList<ConsolidarPedido> mLstConsolidarPedido;
    private RecyclerView rvListaPedidos;
    private RVConsolidarPedidoAdapter mRVConsolidarPedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consolidar_pedido);

        rvListaPedidos = (RecyclerView) findViewById(R.id.rvListaPedidos);

        rvListaPedidos.setHasFixedSize(true);
        rvListaPedidos.setLayoutManager(new LinearLayoutManager(ConsolidarPedidoActivity.this));

        mLstConsolidarPedido = new ArrayList<>();

        mRVConsolidarPedidoAdapter = new RVConsolidarPedidoAdapter(ConsolidarPedidoActivity.this);



        rvListaPedidos.setAdapter(mRVConsolidarPedidoAdapter);
    }

    @Override
    public void onSelectedItem(ConsolidarPedido cPerdido, int position) {

    }
}
