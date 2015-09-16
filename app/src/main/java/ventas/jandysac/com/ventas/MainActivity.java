package ventas.jandysac.com.ventas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private ImageButton btnLogin;
    private final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        //getActionBar().hide();

        btnLogin = (ImageButton) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(btnLoginOnClickListener);
    }

    View.OnClickListener btnLoginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(MainActivity.this, BusquedaCliente.class);
            Intent intent = new Intent(MainActivity.this, BusquedaProducto.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };
}
