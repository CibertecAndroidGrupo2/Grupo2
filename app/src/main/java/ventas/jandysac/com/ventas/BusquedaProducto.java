package ventas.jandysac.com.ventas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import ventas.jandysac.com.ventas.adapter.recyclerview.RVProductoAdapter;
import ventas.jandysac.com.ventas.dao.DataBaseHelper;
import ventas.jandysac.com.ventas.dao.PedidoDAO;
import ventas.jandysac.com.ventas.dao.ProductoDAO;
import ventas.jandysac.com.ventas.entities.Pedido;
import ventas.jandysac.com.ventas.entities.PedidoDetalle;
import ventas.jandysac.com.ventas.entities.Producto;
import ventas.jandysac.com.ventas.ui.UiProductoPrecio;

/**
 * Created by Rodolfo on 09/09/2015.
 */
public class BusquedaProducto extends AppCompatActivity implements RVProductoAdapter.RVProductoAdapterCallBack {
    private EditText txtBusquedaProducto;
    private RecyclerView rvProducto;
    private RVProductoAdapter rvProductoAdapter;
    private DataBaseHelper dataBaseHelper;
    private final static int REQUEST_CODE = 1;
    public final static String CLAVE = "CLAVE", POS = "POS";
    public final static int CODE1 = 1;
    DecimalFormat formatDec = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_producto);
        //getSupportActionBar().setIcon(R.drawable.ic_lock_black_24dp);
        txtBusquedaProducto = (EditText) findViewById(R.id.txtBusquedaProducto);
        rvProducto = (RecyclerView) findViewById(R.id.rvProducto);

        try {
            dataBaseHelper = new DataBaseHelper(BusquedaProducto.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        rvProductoAdapter = new RVProductoAdapter(BusquedaProducto.this);
        rvProducto.setHasFixedSize(true);
        rvProducto.setLayoutManager(new LinearLayoutManager(BusquedaProducto.this));
        rvProducto.setAdapter(rvProductoAdapter);
        getSupportActionBar().setTitle("Productos (" + String.valueOf(rvProductoAdapter.getItemCount()) + ")");
        txtBusquedaProducto.addTextChangedListener(txtBusquedaProductoTextWatcher);
    }

    TextWatcher txtBusquedaProductoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //getSupportActionBar().setTitle("Productos (" + String.valueOf(rvProductoAdapter.getItemCount()) + ")");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            rvProductoAdapter.getFilter().filter(txtBusquedaProducto.getText().toString().trim());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    @Override
    public void onProductoClick(final Producto producto) {
        //Intent intent = new Intent(BusquedaProducto.this, UiProductoPrecio.class);
        //intent.putExtra(CLAVE, producto);
        //startActivityForResult(intent, CODE1);


//        AlertDialog.Builder builder = new AlertDialog.Builder(BusquedaProducto.this);
//        LayoutInflater inflater = BusquedaProducto.this.getLayoutInflater();
//
//        builder.setView(inflater.inflate(R.layout.ui_producto_precio, null));
//        builder.setTitle(producto.getDescripcion())
//                .setIcon(R.drawable.ic_lock_black_24dp);
//
//        // A�adimos los botones
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // Click OK
//                Toast.makeText(BusquedaProducto.this, producto.getCodigo(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // Click CANCEL
//            }
//        });
//        // Set other dialog properties
//
//        // Creamos u mostramos el AlertDialog
//        AlertDialog dialog = builder.create();
//        TextView texto = (TextView) dialog.findViewById(R.id.txtUiProductoPrecio);
//        texto.setText("11.11");
//        dialog.show();

        final Dialog dialog = new Dialog(BusquedaProducto.this);
        dialog.setContentView(R.layout.ui_producto_precio);
        dialog.setTitle(producto.getDescripcion());

        TextView txtUiProductoPrecio = (TextView) dialog.findViewById(R.id.txtUiProductoPrecio);
        txtUiProductoPrecio.setText(String.valueOf(formatDec.format(producto.getPrecio())));

        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        Button dialogButtonOK = (Button) dialog.findViewById(R.id.dialogButtonOK);

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtUiProductoCantidad = (TextView) dialog.findViewById(R.id.txtUiProductoCantidad);
                PedidoDetalle pedidodetalle = getIntent().getParcelableExtra(DatosCliente.ARG_CLIENTE);
                PedidoDetalle pedido = new PedidoDetalle();
                pedido.setId_Movimiento_Venta(pedidodetalle.getId_Movimiento_Venta());
                pedido.setCodigo_Producto(producto.getCodigo());
                pedido.setCantidad(Double.valueOf(txtUiProductoCantidad.getText().toString()));
                pedido.setPrecio(Double.valueOf(producto.getPrecio()));

                PedidoDAO dataGuardar = new PedidoDAO();

                //if (IdPersona != -1) {
                /*INSERTAMOS*/
                //    dataGuardar.updatePersona(persona);
                //} else {
                    dataGuardar.addPedidoDetalle(pedido);
                    Toast.makeText(BusquedaProducto.this, "Producto "+producto.getCodigo()+" fue añadido...", Toast.LENGTH_SHORT).show();
                //}





                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.itLogout) {
            getSharedPreferences(getPackageName(), MODE_PRIVATE).edit().clear().commit();

            Intent intent = new Intent(BusquedaProducto.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
