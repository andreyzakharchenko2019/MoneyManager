package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.Place;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {

    public static final String SHOW_PLACE_LIST_SQL_QUERY = "select * from place";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    public static final String NAME_COLUMN_NAME_PLACE_IN_DATABASE = "name";

    public static final Logger LOGGER = Logger.getLogger(PlaceDAO.class);

    public List<Place> placeList (User user) {
        List<Place> placeList = new ArrayList<Place>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();;

        try (PreparedStatement stmt = con.prepareStatement(SHOW_PLACE_LIST_SQL_QUERY)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Place place = new Place();
                place = setParametersToPlace(place, rs);
                placeList.add(place);
            }

        } catch (SQLException e) {
            LOGGER.error("PreparedStatement showPlaceList", e);
        } finally {
            connectionPool.putBack(con);
        }

        return placeList;
    }

    private Place setParametersToPlace(Place place, ResultSet rs) throws SQLException {
        place.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        place.setName(rs.getString(NAME_COLUMN_NAME_PLACE_IN_DATABASE));

        return place;
    }
}
