package ventas.jandysac.com.ventas.dao;

import android.database.Cursor;

import ventas.jandysac.com.ventas.entities.Parametro;
import ventas.jandysac.com.ventas.entities.Usuario;

/**
 * Created by JoseKoji on 16/09/2015.
 */
public class UsuarioDAO {
    public Usuario findUsuario(String nombre) {
        Usuario usuario = new Usuario();
        Cursor cursor = null;
        String[] args = new String[]{nombre};

        try {
            cursor = DataBaseHelper.myDataBase.query("Usuario", null, "nombre=?", args, null, null, null);

            if (cursor.moveToFirst()) {
                usuario.setNombre(cursor.isNull(cursor.getColumnIndex("nombre")) ? "" : cursor.getString(cursor.getColumnIndex("nombre")));
                usuario.setContrasenia(cursor.isNull(cursor.getColumnIndex("contrasenia")) ? "" : cursor.getString(cursor.getColumnIndex("contrasenia")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return usuario;
    }
}