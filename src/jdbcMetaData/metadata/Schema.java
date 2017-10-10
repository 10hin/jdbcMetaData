package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Schema {

    private String tableCatalog;
    @NonNull
    private String tableSchem;

    public static List<Schema> of(ResultSet rows) throws SQLException {
        List<Schema> schemas = new ArrayList<>();
        while (rows.next()) {
            final String tableCatalog = rows.getString("TABLE_CATALOG");
            final String tableSchem = rows.getString("TABLE_SCHEM");
            final Schema schema = new Schema(tableSchem);
            schema.setTableCatalog(tableCatalog);
            schemas.add(schema);
        }
        return schemas;
    }

}
