package org.karpukhin.sportstore.core.dao;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.model.SkiBoot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 18.10.12
 */
public class SkiBootDaoImpl implements SkiBootDao {

    public static final String SQL_INSERT_SKI_BOOT =
            "INSERT INTO SKI_BOOT (BRAND, NAME, ARTICLE, FLEX_INDEX, MIN_SIZE, MAX_SIZE, DESCRIPTION, COLOR, PRICE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_SKI_BOOT =
            "UPDATE SKI_BOOT SET BRAND = ?, NAME = ?, ARTICLE = ?, FLEX_INDEX = ?, MIN_SIZE = ?, MAX_SIZE = ?, DESCRIPTION = ?, COLOR = ?, PRICE = ? " +
            "WHERE SKI_BOOT_ID = ?";

    public static final String SQL_REMOVE_SKI_BOOT =
            "DELETE FROM SKI_BOOT WHERE SKI_BOOT_ID = ?";

    public static final String SQL_SELECT_BY_ID =
            "SELECT SKI_BOOT_ID, BRAND, NAME, ARTICLE, FLEX_INDEX, MIN_SIZE, MAX_SIZE, DESCRIPTION, COLOR, PRICE " +
            "FROM SKI_BOOT WHERE SKI_BOOT_ID = ?";

    public static final String SQL_SELECT_ALL =
            "SELECT SKI_BOOT_ID, BRAND, NAME, ARTICLE, FLEX_INDEX, MIN_SIZE, MAX_SIZE, DESCRIPTION, COLOR, PRICE " +
            "FROM SKI_BOOT";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE SKI_BOOT (" +
            "SKI_BOOT_ID INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT SKI_BOOT_PK PRIMARY KEY, " +
            "BRAND VARCHAR(50) NOT NULL, " +
            "NAME VARCHAR(100) NOT NULL, " +
            "ARTICLE VARCHAR(25) NOT NULL, " +
            "FLEX_INDEX INTEGER NOT NULL, " +
            "MIN_SIZE DECIMAL(5,1) NOT NULL, " +
            "MAX_SIZE DECIMAL(5,1) NOT NULL, " +
            "DESCRIPTION VARCHAR(255), " +
            "COLOR VARCHAR(50), " +
            "PRICE DECIMAL(10, 2) NOT NULL" +
            ")";

    private Connection connection;

    public SkiBootDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSkiBoot(SkiBoot skiBoot) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_SKI_BOOT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, skiBoot.getBrand());
            statement.setString(2, skiBoot.getName());
            statement.setString(3, skiBoot.getArticle());
            statement.setInt(4, skiBoot.getFlexIndex());
            statement.setBigDecimal(5, skiBoot.getMinSize());
            statement.setBigDecimal(6, skiBoot.getMaxSize());
            statement.setString(7, skiBoot.getDescription());
            statement.setString(8, skiBoot.getColor());
            statement.setBigDecimal(9, skiBoot.getPrice());
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were inserted but 1 was expected only", rows);
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                skiBoot.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public void updateSkiBoot(SkiBoot skiBoot) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_SKI_BOOT);
            statement.setString(1, skiBoot.getBrand());
            statement.setString(2, skiBoot.getName());
            statement.setString(3, skiBoot.getArticle());
            statement.setInt(4, skiBoot.getFlexIndex());
            statement.setBigDecimal(5, skiBoot.getMinSize());
            statement.setBigDecimal(6, skiBoot.getMaxSize());
            statement.setString(7, skiBoot.getDescription());
            statement.setString(8, skiBoot.getColor());
            statement.setBigDecimal(9, skiBoot.getPrice());
            statement.setInt(10, skiBoot.getId());
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were updated but 1 was expected only", rows);
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public void removeSkiBootById(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_REMOVE_SKI_BOOT);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were deleted but 1 was expected only", rows);
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public SkiBoot findSkiBootById(int id) {
        SkiBoot skiBoot = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                skiBoot = map(resultSet);
                if (resultSet.next()) {
                    throw new ApplicationException("More than one row was selected but 1 was expected only");
                }
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
        return skiBoot;
    }

    @Override
    public List<SkiBoot> findAllSkiBoots() {
        List<SkiBoot> result = new ArrayList<SkiBoot>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                result.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
        return result;
    }

    public SkiBoot map(ResultSet resultSet) throws SQLException {
        SkiBoot result = new SkiBoot();
        result.setId(resultSet.getInt("SKI_BOOT_ID"));
        result.setBrand(resultSet.getString("BRAND"));
        result.setName(resultSet.getString("NAME"));
        result.setArticle(resultSet.getString("ARTICLE"));
        result.setFlexIndex(resultSet.getInt("FLEX_INDEX"));
        result.setMinSize(resultSet.getBigDecimal("MIN_SIZE"));
        result.setMaxSize(resultSet.getBigDecimal("MAX_SIZE"));
        result.setDescription(resultSet.getString("DESCRIPTION"));
        result.setColor(resultSet.getString("COLOR"));
        result.setPrice(resultSet.getBigDecimal("PRICE"));
        return result;
    }

    public void createSkiBootTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new ApplicationException("SKI_BOOT table was not created", e);
        } finally {
            close(statement, null);
        }
    }

    private void close(Statement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
