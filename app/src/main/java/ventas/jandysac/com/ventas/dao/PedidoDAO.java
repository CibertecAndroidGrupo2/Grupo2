package ventas.jandysac.com.ventas.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import ventas.jandysac.com.ventas.entities.PedidoDetalle;

/**
 * Created by ph on 12/09/2015.
 */
public class PedidoDAO {
    public ArrayList<PedidoDetalle> listPedidoDetalle() {
        ArrayList<PedidoDetalle> listPedidoDetalle = new ArrayList<>();
        Cursor cursor = null;

        try {
            String query = "SELECT mv.codigo_cliente, mv.importe_neto, mvd.codigo_producto, p.descripcion, mvd.cantidad, mvd.precio, mvd.importe_neto FROM movimiento_venta mv LEFT JOIN movimiento_venta_detalle mvd ON mvd.id_movimiento_venta = mv.id_movimiento_venta LEFT JOIN producto p ON p.codigo= mvd.codigo_producto WHERE mv.codigo_cliente = ? ";
            //String query = "select * from movimiento_venta_detalle WHERE codigo_producto=?";
            cursor = DataBaseHelper.myDataBase.rawQuery(query, new String[]{"00017865"});
            //cursor = DataBaseHelper.myDataBase.query("movimiento_venta_detalle", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    PedidoDetalle pedidodetalle = new PedidoDetalle();
                    pedidodetalle.setCodigo_Producto(cursor.isNull(cursor.getColumnIndex("codigo_producto")) ? "" : cursor.getString(cursor.getColumnIndex("codigo_producto")));
                    pedidodetalle.setCantidad(cursor.isNull(cursor.getColumnIndex("cantidad")) ? 0 : cursor.getDouble(cursor.getColumnIndex("cantidad")));
                    pedidodetalle.setPrecio(cursor.isNull(cursor.getColumnIndex("precio")) ? 0 : cursor.getDouble(cursor.getColumnIndex("precio")));
                    listPedidoDetalle.add(pedidodetalle);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return listPedidoDetalle;
    }
    public void addPedidoDetalle(PedidoDetalle pedidodetalle) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id_movimiento_venta", 1);
            cv.put("codigo_producto", pedidodetalle.getCodigo_Producto());
            cv.put("cantidad", pedidodetalle.getCantidad());
            cv.put("precio", pedidodetalle.getPrecio());
            cv.put("importe_bruto", 0);
            cv.put("importe_igv", 0);
            cv.put("importe_neto", pedidodetalle.getPrecio()*pedidodetalle.getCantidad());
            DataBaseHelper.myDataBase.insert("movimiento_venta_detalle", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
