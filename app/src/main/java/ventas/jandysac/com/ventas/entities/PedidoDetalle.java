package ventas.jandysac.com.ventas.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rodolfo on 09/09/2015.
 */
public class PedidoDetalle implements Parcelable {
    private String codigo_producto;
    private double precio;
    private double cantidad;

    public PedidoDetalle() {
    }

    public String getCodigo_Producto() {
        return codigo_producto;
    }

    public void setCodigo_Producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    protected PedidoDetalle(Parcel in) {
        codigo_producto = in.readString();
        precio = in.readDouble();
        cantidad = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo_producto);
        dest.writeDouble(precio);
        dest.writeDouble(cantidad);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PedidoDetalle> CREATOR = new Parcelable.Creator<PedidoDetalle>() {
        @Override
        public PedidoDetalle createFromParcel(Parcel in) {
            return new PedidoDetalle(in);
        }

        @Override
        public PedidoDetalle[] newArray(int size) {
            return new PedidoDetalle[size];
        }
    };
}