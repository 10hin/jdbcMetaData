package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class ImportedKey {

    private String pktableCat;
    private String pktableSchem;
    @NonNull
    private String pktaleName;
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

    public static List<ImportedKey> of(ResultSet rows) throws SQLException {
        List<ImportedKey> exportedKeys = new ArrayList<>();
        while (rows.next()) {
            final String pktableName = rows.getString("PKTABLE_NAME");
            final String pkcolumnName = rows.getString("PKCOLUMN_NAME");
            final String fktableName = rows.getString("FKTABLE_NAME");
            final String fkcolumnName = rows.getString("FKCOLUMN_NAME");
            ImportedKey exportedKey = new ImportedKey(pktableName, pkcolumnName, fktableName, fkcolumnName);
            exportedKey.setPktableCat(rows.getString("PKTABLE_CAT"));
            exportedKey.setPktableSchem(rows.getString("PKTABLE_SCHEM"));
            exportedKey.setFktableCat(rows.getString("FKTABLE_CAT"));
            exportedKey.setFktableSchem(rows.getString("FKTABLE_SCHEM"));
            exportedKey.setKeySeq(rows.getShort("KEY_SEQ"));
            exportedKey.setUpdateRule(rows.getShort("UPDATE_RULE"));
            exportedKey.setDeleteRule(rows.getShort("DELETE_RULE"));
            exportedKey.setFkName(rows.getString("FK_NAME"));
            exportedKey.setPkName(rows.getString("PK_NAME"));
            exportedKey.setDeferrability(rows.getShort("DEFERRABILITY"));
            exportedKeys.add(exportedKey);
        }
        return exportedKeys;
    }

}
