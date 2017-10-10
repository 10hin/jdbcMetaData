package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class PrimaryKey {

    private String tableCat;
    private String tableSchem;
    @NonNull
    private String tableName;
    @NonNull
    private String columnName;
    private short keySeq;
    private String pkName;

    public static List<PrimaryKey> of(ResultSet rows) throws SQLException {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        while (rows.next()) {
            final String tableName = rows.getString("TABLE_NAME");
            final String columnName = rows.getString("COLUMN_NAME");
            final PrimaryKey primaryKey = new PrimaryKey(tableName, columnName);
            primaryKey.setTableCat(rows.getString("TABLE_CAT"));
            primaryKey.setTableSchem(rows.getString("TABLE_SCHEM"));
            primaryKey.setKeySeq(rows.getShort("KEY_SEQ"));
            primaryKey.setPkName(rows.getString("PK_NAME"));
            primaryKeys.add(primaryKey);
        }
        return primaryKeys;
    }

}
