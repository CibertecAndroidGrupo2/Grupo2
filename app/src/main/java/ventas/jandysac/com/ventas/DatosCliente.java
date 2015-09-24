package ventas.jandysac.com.ventas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ventas.jandysac.com.ventas.entities.Cliente;

public class DatosCliente extends AppCompatActivity {

    public final static String ARG_COD_CLIENTE = "arg_cod_cliente";
    public final static String ARG_COORDENADAS = "arg_coordenadas";
    public final static String ARG_NOMBRE_CLIENTE= "arg_nombre_cliente";
    TextView tvClienteCodigo, tvClienteNombre, tvClienteDireccion, tvClienteTipoDoc;
    Button btMapa, btPedido;
    String coordenadas;
    String codigoCliente;
    String nombreCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);

        tvClienteCodigo = (TextView) findViewById(R.id.tvClienteCodigo);
        tvClienteNombre = (TextView) findViewById(R.id.tvClienteNombre);
        tvClienteDireccion = (TextView) findViewById(R.id.tvClienteDireccion);
        tvClienteTipoDoc = (TextView) findViewById(R.id.tvClienteTipoDoc);
        btMapa = (Button) findViewById(R.id.btMapa);
        btPedido = (Button) findViewById(R.id.btPedido);


        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(BusquedaCliente.ARG_CLIENTE)) {
            Cliente cliente = getIntent().getParcelableExtra(BusquedaCliente.ARG_CLIENTE);


            tvClienteCodigo.setText(cliente.getCodigo());
            tvClienteNombre.setText(cliente.getNombre_completo());
            tvClienteDireccion.setText(cliente.getDireccion());
            tvClienteTipoDoc.setText(cliente.getTipo_doc());
            coordenadas = cliente.getCoodenadas();
            codigoCliente = cliente.getCodigo();
            nombreCliente = cliente.getNombre_completo();

            btMapa.setOnClickListener(btMapaOnClickListener);
            btPedido.setOnClickListener(btPedidoOnClickListener);
        }


    }

    View.OnClickListener btMapaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DatosCliente.this, MapaActivity.class);
            intent.putExtra(ARG_COORDENADAS, coordenadas);
            intent.putExtra(ARG_NOMBRE_CLIENTE, nombreCliente);
            startActivity(intent);

        }
    };

    View.OnClickListener btPedidoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TODO cambiar BusquedaProducto.class por la actividad de Pedido
            Intent intent = new Intent(DatosCliente.this, DetallePedido.class);
            intent.putExtra(ARG_COD_CLIENTE, codigoCliente);
            startActivity(intent);

        }
    };
}
