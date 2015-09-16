package ventas.jandysac.com.ventas.dao;

import android.content.ContentValues;

import ventas.jandysac.com.ventas.entities.PedidoDetalle;

/**
 * Created by ph on 12/09/2015.
 */
public class PedidoDAO {

    public void addPedidoDetalle(PedidoDetalle pedidodetalle) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id_movimiento_venta", 1);
            cv.put("codigo_producto", pedidodetalle.getCodigo_Producto());
            cv.put("cantidad", pedidodetalle.getCantidad());
            cv.put("precio", pedidodetalle.getPrecio());
            cv.put("importe_bruto", 0);
            cv.put("importe_igv", 0);
            cv.put("importe_neto", 0);
            DataBaseHelper.myDataBase.insert("movimiento_venta_detalle", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
