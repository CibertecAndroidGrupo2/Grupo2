package ventas.jandysac.com.ventas.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GaryV on 24/09/2015.
 */

public class ConsolidarPedido implements Parcelable {
    private String nombre;
    private int items;
    private double total;


    public ConsolidarPedido() {
    }

    public ConsolidarPedido(String nombre, int items, double total) {
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

    protected ConsolidarPedido(Parcel in) {
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
    public static final Parcelable.Creator<ConsolidarPedido> CREATOR = new Parcelable.Creator<ConsolidarPedido>() {
        @Override
        public ConsolidarPedido createFromParcel(Parcel in) {
            return new ConsolidarPedido(in);
        }

        @Override
        public ConsolidarPedido[] newArray(int size) {
            return new ConsolidarPedido[size];
        }
    };
}