package jdbcMetaData.metadata;

import java.sql.ResultSet;
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
    @NonNull
    private String remarks;
    private String typeCat;
    private String typeSchem;
    private String typeName;
    private String selfReferencingColName;
    private String refGeneration;

    public static List<Table> of(ResultSet rows) throws SQLException {
        final List<Table> tables = new ArrayList<>();
        while (rows.next()) {
            final String name = rows.getString("TABLE_NAME");
            final String type = rows.getString("TABLE_TYPE");
            final String remarks = rows.getString("REMARKS");
            final Table table = new Table(name, type, remarks);
            table.setTableCat(rows.getString("TABLE_CATALOG"));
            table.setTableSchem(rows.getString("TABLE_SCHEMA"));
            table.setTypeCat(rows.getString("TYPE_CAT"));
            table.setTypeSchem(rows.getString("TYPE_SCHEM"));
            table.setTypeName(rows.getString("TYPE_NAME"));
            table.setSelfReferencingColName(rows.getString("SELF_REFERENCING_COL_NAME"));
            table.setRefGeneration(rows.getString("REF_GENERATION"));
            tables.add(table);
        }
        return tables;
    }

}
