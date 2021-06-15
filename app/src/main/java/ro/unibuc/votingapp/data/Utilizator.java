package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity ( tableName = "Utilizator", primaryKeys = { "idUtilizator" } )
public final class Utilizator {
    @ColumnInfo ( name = "idUtilizator" )
    @SerializedName ( "idUtilizator" )
    @Expose
    @NonNull
    private final String idUtilizator;

    @ColumnInfo ( name = "email" )
    @SerializedName ( "email" )
    @Expose
    @NonNull
    private final String email;

    @ColumnInfo ( name = "dataNastere" )
    @SerializedName ( "dataNastere" )
    @Expose
    @NonNull
    private final String dataNastere;

    @ColumnInfo ( name = "serie" )
    @SerializedName ( "serie" )
    @Expose
    @NonNull
    private String serie;

    @ColumnInfo ( name = "nrCI" )
    @SerializedName ( "nrCI" )
    @Expose
    @NonNull
    private String nrCI;

    @ColumnInfo ( name = "nume" )
    @SerializedName ( "nume" )
    @Expose
    @NonNull
    private String nume;

    @ColumnInfo ( name = "adresa" )
    @SerializedName ( "adresa" )
    @Expose
    @NonNull
    private String adresa;

    @ColumnInfo ( name = "locNastere" )
    @SerializedName ( "locNastere" )
    @Expose
    @NonNull
    private final String locNastere;

    @ColumnInfo ( name = "emitere" )
    @SerializedName ( "emitere" )
    @Expose
    @NonNull
    private String emitere;

    public Utilizator( String idUtilizator, String email, String dataNastere, String serie, String nrCI, String nume, String adresa, String locNastere, String emitere ) {
        this.idUtilizator = idUtilizator;
        this.email = email;
        this.dataNastere = dataNastere;
        this.serie = serie;
        this.nrCI = nrCI;
        this.nume = nume;
        this.adresa = adresa;
        this.locNastere = locNastere;
        this.emitere = emitere;
    }

    public String getIdUtilizator() {
        return idUtilizator;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public String getSerie() {
        return serie;
    }

    public String getNrCI() {
        return nrCI;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getLocNastere() {
        return locNastere;
    }

    public String getEmitere() {
        return emitere;
    }
}