package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity ( tableName = "Locatie", primaryKeys = { "idLocatie" } )
public final class Locatie {
    @ColumnInfo ( name = "idLocatie" )
    @SerializedName ( "idLocatie" )
    @Expose
    @NonNull
    private final String idLocatie;

    @ColumnInfo ( name = "tara" )
    @SerializedName ( "tara" )
    @Expose
    @NonNull
    private final String tara;

    @ColumnInfo ( name = "oras" )
    @SerializedName ( "oras" )
    @Expose
    private final String oras;

    @ColumnInfo ( name = "adresa" )
    @SerializedName ( "adresa" )
    @Expose
    private final String adresa;

    public Locatie( @NonNull String idLocatie, @NonNull String tara, String oras, String adresa ) {
        this.idLocatie = idLocatie;
        this.tara = tara;
        this.oras = oras;
        this.adresa = adresa;
    }

    @NonNull
    public String getIdLocatie() {
        return idLocatie;
    }

    @NonNull
    public String getTara() {
        return tara;
    }

    public String getOras() {
        return oras;
    }

    public String getAdresa() {
        return adresa;
    }
}