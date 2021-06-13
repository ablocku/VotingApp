package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Stire",primaryKeys = {"idStire", "idAlegere"}, foreignKeys = @ForeignKey(entity = Alegere.class, parentColumns = "idAlegere", childColumns = "idAlegere"))
public final class Stire {
    @ColumnInfo(name = "idAlegere")
    @SerializedName("idAlegere")
    @Expose
    @NonNull
    private final String idAlegere;

    @ColumnInfo(name = "idStire")
    @SerializedName("idStire")
    @Expose
    @NonNull
    private final String idStire;

    @ColumnInfo(name = "titluStire")
    @SerializedName("titluStire")
    @Expose
    @NonNull
    private final String titluStire;

    @ColumnInfo(name = "dataStire")
    @SerializedName("dataStire")
    @Expose
    @NonNull
    private final String dataStire;


    @ColumnInfo(name = "continut")
    @SerializedName("continut")
    @Expose
    @NonNull
    private final String continut;

    public Stire(@NonNull String idAlegere, @NonNull String idStire, @NonNull String titluStire, @NonNull String dataStire, @NonNull String continut) {
        this.idAlegere = idAlegere;
        this.idStire = idStire;
        this.titluStire = titluStire;
        this.dataStire = dataStire;
        this.continut = continut;
    }
}
