package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Alegere", foreignKeys = @ForeignKey(entity = Locatie.class, parentColumns = "idLocatie",childColumns = "idLocatie"))
public final class Alegere {
    @PrimaryKey
    @ColumnInfo(name = "idAlegere")
    @SerializedName("idAlegere")
    @Expose
    @NonNull
    private final String idAlegere;

    @ColumnInfo(name = "idLocatie")
    @SerializedName("idLocatie")
    @Expose
    @NonNull
    private final String idLocatie;

    @ColumnInfo(name = "data")
    @SerializedName("data")
    @Expose
    @NonNull
    private final String data;

    @ColumnInfo(name = "tipVot")
    @SerializedName("tipVot")
    @Expose
    @NonNull
    private final String tipVot;

    @ColumnInfo(name = "titlu")
    @SerializedName("titlu")
    @Expose
    @NonNull
    private final String titlu;

    public Alegere(@NonNull String idAlegere, @NonNull String idLocatie, @NonNull String data, @NonNull String tipVot, @NonNull String titlu) {
        this.idAlegere = idAlegere;
        this.idLocatie = idLocatie;
        this.data = data;
        this.tipVot = tipVot;
        this.titlu = titlu;
    }
}
