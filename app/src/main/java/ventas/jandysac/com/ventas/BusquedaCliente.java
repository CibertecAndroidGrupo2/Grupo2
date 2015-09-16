package ventas.jandysac.com.ventas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import ventas.jandysac.com.ventas.adapter.recyclerview.RVClienteAdapter;
import ventas.jandysac.com.ventas.dao.DataBaseHelper;
import ventas.jandysac.com.ventas.entities.Cliente;

/**
 * Created by Rodolfo on 09/09/2015.
 */
public class BusquedaCliente extends AppCompatActivity implements RVClienteAdapter.RVClienteAdapterCallBack {
    private EditText txtBusqueda;
    private RecyclerView rvCliente;
    private RVClienteAdapter rvClienteAdapter;
    private DataBaseHelper dataBaseHelper;
    private final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_cliente);

        //getSupportActionBar().setIcon(R.drawable.ic_lock_black_24dp);
        txtBusqueda = (EditText) findViewById(R.id.txtBusqueda);
        rvCliente = (RecyclerView) findViewById(R.id.rvCliente);

        try {
            dataBaseHelper = new DataBaseHelper(BusquedaCliente.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        rvClienteAdapter = new RVClienteAdapter(BusquedaCliente.this);
        rvCliente.setHasFixedSize(true);
        rvCliente.setLayoutManager(new LinearLayoutManager(BusquedaCliente.this));
        rvCliente.setAdapter(rvClienteAdapter);
        getSupportActionBar().setTitle("Clientes (" + String.valueOf(rvClienteAdapter.getItemCount()) + ")");
        txtBusqueda.addTextChangedListener(txtBusquedaTextWatcher);
    }

    TextWatcher txtBusquedaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //getSupportActionBar().setTitle("Clientes (" + String.valueOf(rvClienteAdapter.getItemCount()) + ")");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            rvClienteAdapter.getFilter().filter(txtBusqueda.getText().toString().trim());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onClienteClick(Cliente cliente) {
        Intent intent = new Intent(BusquedaCliente.this, DatosCliente.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
