package ventas.jandysac.com.ventas.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.entities.Cliente;

/**
 * Created by Rodolfo on 10/09/2015.
 */
public class ClienteDAO {
    public ArrayList<Cliente> listCliente() {
        ArrayList<Cliente> listCliente = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.query("cliente", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cursor.isNull(cursor.getColumnIndex("codigo")) ? "" : cursor.getString(cursor.getColumnIndex("codigo")));
                    cliente.setNombre_completo(cursor.isNull(cursor.getColumnIndex("nombre_completo")) ? "" : cursor.getString(cursor.getColumnIndex("nombre_completo")));
                    listCliente.add(cliente);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return listCliente;
    }

}
