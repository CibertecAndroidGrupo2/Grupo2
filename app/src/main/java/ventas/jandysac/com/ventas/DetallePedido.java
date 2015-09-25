package ventas.jandysac.com.ventas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.adapter.listview.LVPedidoDetalleAdapter;
import ventas.jandysac.com.ventas.adapter.recyclerview.RVPedidoDetalleAdapter;
import ventas.jandysac.com.ventas.dao.DataBaseHelper;
import ventas.jandysac.com.ventas.entities.PedidoDetalle;
import ventas.jandysac.com.ventas.dao.PedidoDAO;

public class DetallePedido extends AppCompatActivity implements RVPedidoDetalleAdapter.RVPedidoDetalleAdapterCallBack {

    TextView txtTotalPedido, txtItemsPedido;
    private ImageButton btnAgregarProducto, btnGuardarPedido;
    private ArrayList mLstPedidoCabecera;
    private ArrayList<PedidoDetalle> mLstPedidoDetalle;
    private RecyclerView rvPedidoDetalle;
    private ListView lvPedidoDetalle;
    private RVPedidoDetalleAdapter rvPedidoDetalleAdapter;
    private LVPedidoDetalleAdapter mLVPedidoDetalleAdapter;
    private DataBaseHelper dataBaseHelper;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        btnAgregarProducto = (ImageButton) findViewById(R.id.btnAgregarProducto);
        btnGuardarPedido = (ImageButton) findViewById(R.id.btnGuardarPedido);

        txtTotalPedido = (TextView) findViewById(R.id.txtTotalPedido);
        txtItemsPedido = (TextView) findViewById(R.id.txtItemsPedido);
        //rvPedidoDetalle = (RecyclerView) findViewById(R.id.rvPedidoDetalle);
        try {
            dataBaseHelper = new DataBaseHelper(DetallePedido.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(DatosCliente.ARG_COD_CLIENTE)) {
            String codigo_cliente = getIntent().getStringExtra(DatosCliente.ARG_COD_CLIENTE);
            PedidoDetalle pedidodetalle = getIntent().getParcelableExtra(DatosCliente.ARG_CLIENTE);
            pedidodetalle.setCodigo_Cliente(codigo_cliente);
            PedidoDAO pedido = new PedidoDAO();
            mLstPedidoCabecera= new ArrayList<>();
            //mLstPedidoCabecera = pedido.listPedidoCabecera(codigo_cliente);

            //PedidoDetalle pedidocabecera = new PedidoDAO().listPedidoCabecera(codigo_cliente);
            txtTotalPedido.setText(String.valueOf(pedidodetalle.getImporte_Total()));
            txtItemsPedido.setText("1");


            lvPedidoDetalle = (ListView) findViewById(R.id.lvPedidoDetalle);
            PedidoDetalle pedidoDetalle = new PedidoDetalle();
            pedidoDetalle.setId_Movimiento_Venta(pedidodetalle.getId_Movimiento_Venta());
            PedidoDAO detalleGuardar = new PedidoDAO();
            mLstPedidoDetalle = detalleGuardar.listPedidoDetalle(String.valueOf(pedidodetalle.getId_Movimiento_Venta()));
            mLVPedidoDetalleAdapter = new LVPedidoDetalleAdapter(DetallePedido.this, 0, mLstPedidoDetalle);
            lvPedidoDetalle.setAdapter(mLVPedidoDetalleAdapter);
        }
        btnAgregarProducto.setOnClickListener(btnAgregarProductoOnClickListener);
        btnGuardarPedido.setOnClickListener(btnGuardarPedidoOnClickListener);
    }

    View.OnClickListener btnAgregarProductoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetallePedido.this, BusquedaProducto.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnGuardarPedidoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DetallePedido.this, BusquedaProducto.class);
            startActivity(intent);
        }
    };

    @Override
    public void onPedidoDetalleClick(PedidoDetalle pedidodetalle) {

    }
}
