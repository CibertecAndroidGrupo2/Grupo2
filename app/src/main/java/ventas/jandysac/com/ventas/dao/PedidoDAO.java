package ventas.jandysac.com.ventas.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.IntegerRes;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ventas.jandysac.com.ventas.entities.ConsolidarPedido;
import ventas.jandysac.com.ventas.entities.PedidoDetalle;

/**
 * Created by ph on 12/09/2015.
 */
public class PedidoDAO {
    DecimalFormat formato = new DecimalFormat("###.##");
    //public ArrayList<PedidoDetalle> listPedidoCabecera(String codigo_cliente) {
    public PedidoDetalle listPedidoCabecera(String codigo_cliente) {
        //ArrayList<PedidoDetalle> listPedidoCabecera = new ArrayList<>();
        PedidoDetalle pedidodetalle = new PedidoDetalle();
        Cursor cursor = null;

        try {
            String query = "SELECT mv.id_movimiento_venta, mv.codigo_cliente, SUM(mvd.importe_neto) AS importe_total, COUNT(mvd.id_movimiento_venta) AS items FROM movimiento_venta mv LEFT JOIN movimiento_venta_detalle mvd ON mvd.id_movimiento_venta = mv.id_movimiento_venta WHERE mv.codigo_cliente = ? GROUP BY mv.id_movimiento_venta  ";
            cursor = DataBaseHelper.myDataBase.rawQuery(query, new String[]{String.valueOf(codigo_cliente)});
            if (cursor.moveToFirst()) {
                //do {
                    pedidodetalle.setId_Movimiento_Venta(cursor.isNull(cursor.getColumnIndex("id_movimiento_venta")) ? 0 : cursor.getInt(cursor.getColumnIndex("id_movimiento_venta")));
                    pedidodetalle.setCodigo_Cliente(cursor.isNull(cursor.getColumnIndex("codigo_cliente")) ? "" : cursor.getString(cursor.getColumnIndex("codigo_cliente")));
                    pedidodetalle.setImporte_Total(cursor.isNull(cursor.getColumnIndex("importe_total")) ? 0 : cursor.getDouble(cursor.getColumnIndex("importe_total")));
                    pedidodetalle.setItems(cursor.isNull(cursor.getColumnIndex("items")) ? 0 : cursor.getDouble(cursor.getColumnIndex("items")));
                    //listPedidoCabecera.add(pedidodetalle);
                //} while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return pedidodetalle;
    }

    public ArrayList<PedidoDetalle> listPedidoDetalle(String id_movimiento_venta) {
        ArrayList<PedidoDetalle> listPedidoDetalle = new ArrayList<>();
        Cursor cursor = null;

        try {
            String query = "SELECT mv.codigo_cliente, mvd.codigo_producto, p.descripcion, mvd.cantidad, mvd.precio, mvd.importe_neto FROM movimiento_venta mv LEFT JOIN movimiento_venta_detalle mvd ON mvd.id_movimiento_venta = mv.id_movimiento_venta LEFT JOIN producto p ON p.codigo= mvd.codigo_producto WHERE mv.id_movimiento_venta = ? ";
            cursor = DataBaseHelper.myDataBase.rawQuery(query, new String[]{String.valueOf(id_movimiento_venta)});

            if (cursor.moveToFirst()) {
                do {
                    PedidoDetalle pedidodetalle = new PedidoDetalle();
                    pedidodetalle.setCodigo_Producto(cursor.isNull(cursor.getColumnIndex("codigo_producto")) ? "" : cursor.getString(cursor.getColumnIndex("codigo_producto")));
                    pedidodetalle.setDescripcion(cursor.isNull(cursor.getColumnIndex("descripcion")) ? "" : cursor.getString(cursor.getColumnIndex("descripcion")));
                    pedidodetalle.setCantidad(cursor.isNull(cursor.getColumnIndex("cantidad")) ? 0 : cursor.getDouble(cursor.getColumnIndex("cantidad")));
                    pedidodetalle.setPrecio(cursor.isNull(cursor.getColumnIndex("precio")) ? 0 : cursor.getDouble(cursor.getColumnIndex("precio")));
                    pedidodetalle.setImporte_Neto(cursor.isNull(cursor.getColumnIndex("importe_neto")) ? 0 : cursor.getDouble(cursor.getColumnIndex("importe_neto")));
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

    public long addPedidoCabecera(PedidoDetalle pedidodetalle) {
        long codigo = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put("codigo_empresa", "000001");
            cv.put("codigo_vendedor", "000001");
            cv.put("codigo_cliente", pedidodetalle.getCodigo_Cliente());
            cv.put("estado", 0);
            codigo = DataBaseHelper.myDataBase.insert("movimiento_venta", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return codigo;
    }

    public void addPedidoDetalle(PedidoDetalle pedidodetalle) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("id_movimiento_venta", pedidodetalle.getId_Movimiento_Venta());
            cv.put("codigo_producto", pedidodetalle.getCodigo_Producto());
            cv.put("cantidad", formato.format(pedidodetalle.getCantidad()));
            cv.put("precio", formato.format(pedidodetalle.getPrecio()));
            cv.put("importe_neto", formato.format(pedidodetalle.getPrecio()*pedidodetalle.getCantidad()));
            DataBaseHelper.myDataBase.insert("movimiento_venta_detalle", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ConsolidarPedido> listPedidosAConsolidar(String cod_usuario) {
        ArrayList<ConsolidarPedido> listPedidos = new ArrayList<>();
        Cursor cursor = null;

        try {
            String query = "SELECT mv.id_movimiento_venta, (c.nombres||' '||c.apellido_paterno|| ' '|| c.apellido_materno )  AS cliente, count(mvd.importe_neto ) AS items, SUM(mvd.importe_neto ) AS totalPedido,mv.estado " +
                    "FROM movimiento_venta mv "+
                    " LEFT JOIN movimiento_venta_detalle mvd ON mvd.id_movimiento_venta = mv.id_movimiento_venta " +
                    " LEFT JOIN cliente c ON mv.codigo_cliente = c.codigo  " +
                    " GROUP BY mv.id_movimiento_venta, c.nombres " +
                    " HAVING mv.codigo_vendedor = ? ";

            cursor = DataBaseHelper.myDataBase.rawQuery(query, new String[]{String.valueOf(cod_usuario)});

            if (cursor.moveToFirst()) {
                do {
                    ConsolidarPedido pedidos = new ConsolidarPedido();
                    pedidos.setNombre(cursor.isNull(cursor.getColumnIndex("cliente")) ? "" : cursor.getString(cursor.getColumnIndex("cliente")));
                    pedidos.setItems(cursor.isNull(cursor.getColumnIndex("items")) ? 0 : cursor.getInt(cursor.getColumnIndex("items")));
                    pedidos.setTotal(cursor.isNull(cursor.getColumnIndex("totalPedido")) ? 0 : cursor.getDouble(cursor.getColumnIndex("totalPedido")));
                    pedidos.setEstado(cursor.isNull(cursor.getColumnIndex("estado")) ? 0 : cursor.getInt(cursor.getColumnIndex("estado")));

                    listPedidos.add(pedidos);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return listPedidos;
    }

    public void updateEstadoPedido(ConsolidarPedido pedido) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("estado", pedido.getEstado());
            DataBaseHelper.myDataBase.update("movimiento_venta", cv, "id_movimiento_venta = ?", new String[]{String.valueOf(pedido.getIdPedido())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
