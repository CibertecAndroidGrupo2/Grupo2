package ventas.jandysac.com.ventas.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GaryV on 24/09/2015.
 */

public class ConsolidadPedido implements Parcelable {
    private String nombre;
    private int items;
    private double total;


    public ConsolidadPedido() {
    }

    public ConsolidadPedido(String nombre, int items, double total) {
        this.nombre = nombre;
        this.items = items;
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    protected ConsolidadPedido(Parcel in) {
        nombre = in.readString();
        items = in.readInt();
        total = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(items);
        dest.writeDouble(total);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ConsolidadPedido> CREATOR = new Parcelable.Creator<ConsolidadPedido>() {
        @Override
        public ConsolidadPedido createFromParcel(Parcel in) {
            return new ConsolidadPedido(in);
        }

        @Override
        public ConsolidadPedido[] newArray(int size) {
            return new ConsolidadPedido[size];
        }
    };
}