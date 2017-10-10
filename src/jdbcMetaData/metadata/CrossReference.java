package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class CrossReference {

    private String pktableCat;
    private String pktableSchem;
    @NonNull
    private String pktableName;
    @NonNull
    private String pkcolumnName;
    private String fktableCat;
    private String fktableSchem;
    @NonNull
    private String fktableName;
    @NonNull
    private String fkcolumnName;
    private short keySeq;
    private short updateRule;
    private short deleteRule;
    private String fkName;
    private String pkName;
    private short deferrability;

    public static List<CrossReference> of(ResultSet rows) throws SQLException {
        List<CrossReference> crossReferences = new ArrayList<>();
        while (rows.next()) {
            final String pktableName = rows.getString("PKTABLE_NAME");
            final String pkcolumnName = rows.getString("PKCOLUMN_NAME");
            final String fktableName = rows.getString("FKTABLE_NAME");
            final String fkcolumnName = rows.getString("FKCOLUMN_NAME");
            CrossReference crossReference = new CrossReference(pktableName, pkcolumnName, fktableName, fkcolumnName);
            crossReference.setPktableCat(rows.getString("PKTABLE_CAT"));
            crossReference.setPktableSchem(rows.getString("PKTABLE_SCHEM"));
            crossReference.setFktableCat(rows.getString("FKTABLE_CAT"));
            crossReference.setFktableSchem(rows.getString("FKTABLE_SCHEM"));
            crossReference.setKeySeq(rows.getShort("KEY_SEQ"));
            crossReference.setUpdateRule(rows.getShort("UPDATE_RULE"));
            crossReference.setDeleteRule(rows.getShort("DELETE_RULE"));
            crossReference.setFkName(rows.getString("FK_NAME"));
            crossReference.setPkName(rows.getString("PK_NAME"));
            crossReference.setDeferrability(rows.getShort("DEFERRABILITY"));
            crossReferences.add(crossReference);
        }
        return crossReferences;
    }

}
