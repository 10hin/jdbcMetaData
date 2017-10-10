package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Catalog {

    @NonNull
    private String tableCat;

    public static List<Catalog> of(ResultSet rows) throws SQLException {
        List<Catalog> catalogs = new ArrayList<>();
        while (rows.next()) {
            final String tableCat = rows.getString("TABLE_CAT");
            final Catalog catalog = new Catalog(tableCat);
            catalogs.add(catalog);
        }
        return catalogs;
    }

}
