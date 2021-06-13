package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Locatie")
public final class Locatie {
    @PrimaryKey
    @ColumnInfo(name = "idLocatie")
    @SerializedName("idLocatie")
    @Expose
    @NonNull
    private final String idLocatie;

    @ColumnInfo(name = "tara")
    @SerializedName("tara")
    @Expose
    @NonNull
    private final String tara;

    @ColumnInfo(name = "oras")
    @SerializedName("oras")
    @Expose
    private final String oras;

    @ColumnInfo(name = "adresa")
    @SerializedName("adresa")
    @Expose
    private final String adresa;

    public Locatie(@NonNull String idLocatie, @NonNull String tara, String oras, String adresa) {
        this.idLocatie = idLocatie;
        this.tara = tara;
        this.oras = oras;
        this.adresa = adresa;
    }
}