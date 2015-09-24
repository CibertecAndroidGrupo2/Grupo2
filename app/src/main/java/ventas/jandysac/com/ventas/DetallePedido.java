package ventas.jandysac.com.ventas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ventas.jandysac.com.ventas.adapter.recyclerview.RVPedidoDetalleAdapter;
import ventas.jandysac.com.ventas.dao.DataBaseHelper;
import ventas.jandysac.com.ventas.entities.PedidoDetalle;


public class DetallePedido extends AppCompatActivity implements RVPedidoDetalleAdapter.RVPedidoDetalleAdapterCallBack {
    TextView tvClienteCodigo;
    private RecyclerView rvPedidoDetalle;
    private RVPedidoDetalleAdapter rvPedidoDetalleAdapter;
    private DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        tvClienteCodigo = (TextView) findViewById(R.id.txtTotalPedido);
        rvPedidoDetalle = (RecyclerView) findViewById(R.id.rvPedidoDetalle);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(DatosCliente.ARG_COD_CLIENTE)) {
            String codigo_cliente = getIntent().getStringExtra(DatosCliente.ARG_COD_CLIENTE);
            tvClienteCodigo.setText(codigo_cliente.toString());
        }

        try {
            dataBaseHelper = new DataBaseHelper(DetallePedido.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        rvPedidoDetalleAdapter = new RVPedidoDetalleAdapter(DetallePedido.this);
        rvPedidoDetalle.setHasFixedSize(true);
        rvPedidoDetalle.setLayoutManager(new LinearLayoutManager(DetallePedido.this));
        rvPedidoDetalle.setAdapter(rvPedidoDetalleAdapter);

    }

    @Override
    public void onPedidoDetalleClick(PedidoDetalle pedidodetalle) {

    }
}
