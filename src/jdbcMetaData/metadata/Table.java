package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Table {

    private String tableCat;
    private String tableSchem;
    @NonNull
    private String tableName;
    @NonNull
    private String tableType;
    private String remarks;
    private String typeCat;
    private String typeSchem;
    private String typeName;
    private String selfReferencingColName;
    private String refGeneration;

    public static List<Table> of(ResultSet rows) throws SQLException {
        final List<Table> tables = new ArrayList<>();
        final List<String> colNames = new ArrayList<>();
        ResultSetMetaData rsMetaData = rows.getMetaData();
        int colSize = rsMetaData.getColumnCount();
        for (int cnt = 1; cnt <= colSize; cnt++) {
            colNames.add(rsMetaData.getColumnName(cnt));
        }
        boolean hasTypeCat = colNames.contains("TYPE_CAT");
        boolean hasTypeSchem = colNames.contains("TYPE_SCHEM");
        boolean hasTypeName = colNames.contains("TYPE_NAME");
        boolean hasSelfReferenceingColName = colNames.contains("SELF_REFERENCING_COL_NAME");
        boolean hasRefGeneration = colNames.contains("REF_GENERATION");
        while (rows.next()) {
            final String name = rows.getString("TABLE_NAME");
            final String type = rows.getString("TABLE_TYPE");
            final Table table = new Table(name, type);
            table.setRemarks(rows.getString("REMARKS"));
            table.setTableCat(rows.getString("TABLE_CAT"));
            table.setTableSchem(rows.getString("TABLE_SCHEM"));
            if (hasTypeCat) {
                table.setTypeCat(rows.getString("TYPE_CAT"));
            }
            if (hasTypeSchem) {
                table.setTypeSchem(rows.getString("TYPE_SCHEM"));
            }
            if (hasTypeName) {
                table.setTypeName(rows.getString("TYPE_NAME"));
            }
            if (hasSelfReferenceingColName) {
                table.setSelfReferencingColName(rows.getString("SELF_REFERENCING_COL_NAME"));
            }
            if (hasRefGeneration) {
                table.setRefGeneration(rows.getString("REF_GENERATION"));
            }
            tables.add(table);
        }
        return tables;
    }

}
