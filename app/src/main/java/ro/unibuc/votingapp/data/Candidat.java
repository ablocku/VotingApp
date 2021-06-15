package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity ( tableName = "Candidat", primaryKeys = { "idCandidat", "idAlegere" }, foreignKeys = @ForeignKey ( entity = Alegere.class, parentColumns = "idAlegere", childColumns = "idAlegere" ) )
public final class Candidat {
    @ColumnInfo ( name = "idAlegere" )
    @SerializedName ( "idAlegere" )
    @Expose
    @NonNull
    private final String idAlegere;

    @ColumnInfo ( name = "idCandidat" )
    @SerializedName ( "idCandidat" )
    @Expose
    @NonNull
    private final String idCandidat;

    @ColumnInfo ( name = "numeCandidat" )
    @SerializedName ( "numeCandidat" )
    @Expose
    @NonNull
    private final String numeCandidat;

    @ColumnInfo ( name = "observatii" )
    @SerializedName ( "observatii" )
    @Expose
    private final String observatii;


    public Candidat( @NonNull String idAlegere, @NonNull String idCandidat, @NonNull String numeCandidat, String observatii ) {
        this.idAlegere = idAlegere;
        this.idCandidat = idCandidat;
        this.numeCandidat = numeCandidat;
        this.observatii = observatii;
    }

    @NonNull
    public String getIdAlegere() {
        return idAlegere;
    }

    @NonNull
    public String getIdCandidat() {
        return idCandidat;
    }

    @NonNull
    public String getNumeCandidat() {
        return numeCandidat;
    }

    public String getObservatii() {
        return observatii;
    }
}
