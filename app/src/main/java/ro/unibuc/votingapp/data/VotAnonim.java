package ro.unibuc.votingapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity ( tableName = "VotAnonim", primaryKeys = { "idCandidat", "idAlegere", "idVot" }, foreignKeys = { @ForeignKey ( entity = Candidat.class, parentColumns = { "idAlegere", "idCandidat" }, childColumns = { "idAlegere", "idCandidat" } ) } )
public final class VotAnonim {
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

    @ColumnInfo ( name = "idVot" )
    @SerializedName ( "idVot" )
    @Expose
    @NonNull
    private final String idVot;

    public VotAnonim( @NonNull String idAlegere, @NonNull String idCandidat, @NonNull String idVot ) {
        this.idAlegere = idAlegere;
        this.idCandidat = idCandidat;
        this.idVot = idVot;
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
    public String getIdVot() {
        return idVot;
    }
}
