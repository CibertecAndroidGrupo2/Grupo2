package ventas.jandysac.com.ventas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.adapter.recyclerview.RVConsolidarPedidoAdapter;
import ventas.jandysac.com.ventas.dao.DataBaseHelper;
import ventas.jandysac.com.ventas.entities.ConsolidarPedido;

/**
 * Created by Administrator on 19/09/2015.
 */
public class ConsolidarPedidoActivity extends AppCompatActivity implements RVConsolidarPedidoAdapter.RVConsolidarPedidoAdapterListener {


    private ArrayList<ConsolidarPedido> mLstConsolidarPedido;
    private RecyclerView rvListaPedidos;
    private RVConsolidarPedidoAdapter mRVConsolidarPedidoAdapter;
    private DataBaseHelper dataBaseHelper;

    private TextView tvTotalPedido, tvItemsPedido;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consolidar_pedido);

        tvTotalPedido = (TextView) findViewById(R.id.tvTotalPedido);
        tvItemsPedido = (TextView) findViewById(R.id.tvItemsPedido);

        rvListaPedidos = (RecyclerView) findViewById(R.id.rvListaPedidos);

        rvListaPedidos.setHasFixedSize(true);
        rvListaPedidos.setLayoutManager(new LinearLayoutManager(ConsolidarPedidoActivity.this));

        mLstConsolidarPedido = new ArrayList<>();

        try {
            dataBaseHelper = new DataBaseHelper(ConsolidarPedidoActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String cod_vendedor = "0";

        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        if (sp.contains(MainActivity.ARG_NOMAPE) &&
                !sp.getString(MainActivity.ARG_NOMAPE, "").isEmpty()) {
            cod_vendedor = sp.getString(MainActivity.ARG_CODIGO, "");
        }


        mRVConsolidarPedidoAdapter = new RVConsolidarPedidoAdapter(ConsolidarPedidoActivity.this, cod_vendedor);
        rvListaPedidos.setAdapter(mRVConsolidarPedidoAdapter);

        tvItemsPedido.setText(String.valueOf(mRVConsolidarPedidoAdapter.getItemCount()));
        tvTotalPedido.setText(String.valueOf(mRVConsolidarPedidoAdapter.getTotal()));
    }

    @Override
    public void onSelectedItem(ConsolidarPedido cPerdido, int position) {

    }
}
