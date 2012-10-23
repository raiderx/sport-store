package org.karpukhin.sportstore.core.dao;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.model.Ski;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pavel Karpukhin
 * @since 12.10.12
 */
public class SkiDaoImpl implements SkiDao {

    public static final String SQL_INSERT_SKI =
            "INSERT INTO SKI (BRAND, NAME, ARTICLE, DESCRIPTION, PRICE) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_SKI =
            "UPDATE SKI SET BRAND = ?, NAME = ?, ARTICLE = ?, DESCRIPTION = ?, PRICE = ? " +
            "WHERE SKI_ID = ?";

    public static final String SQL_REMOVE_SKI =
            "DELETE FROM SKI WHERE SKI_ID = ?";

    public static final String SQL_SELECT_BY_ID =
            "SELECT SKI_ID, BRAND, NAME, ARTICLE, DESCRIPTION, PRICE " +
            "FROM SKI WHERE SKI_ID = ?";

    public static final String SQL_SELECT_ALL =
            "SELECT SKI_ID, BRAND, NAME, ARTICLE, DESCRIPTION, PRICE " +
            "FROM SKI ORDER BY SKI_ID";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE SKI (" +
            "SKI_ID INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT SKI_PK PRIMARY KEY, " +
            "BRAND VARCHAR(255) NOT NULL, " +
            "NAME VARCHAR(255) NOT NULL, " +
            "ARTICLE VARCHAR(25) NOT NULL, " +
            "DESCRIPTION VARCHAR(255), " +
            "PRICE DECIMAL(10, 2) NOT NULL" +
            ")";

    private final Logger logger = Logger.getLogger(getClass().getName());

    private Connection connection;

    public SkiDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSki(Ski ski) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_SKI, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ski.getBrand());
            statement.setString(2, ski.getName());
            statement.setString(3, ski.getArticle());
            statement.setString(4, ski.getDescription());
            statement.setBigDecimal(5, ski.getPrice());
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were inserted but 1 was expected only", rows);
            }
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                ski.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public void updateSki(Ski ski) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_SKI);
            statement.setString(1, ski.getBrand());
            statement.setString(2, ski.getName());
            statement.setString(3, ski.getArticle());
            statement.setString(4, ski.getDescription());
            statement.setBigDecimal(5, ski.getPrice());
            statement.setInt(6, ski.getId());
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were updated but 1 was expected only", rows);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public void removeSkiById(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_REMOVE_SKI);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            if (rows != 1) {
                throw new ApplicationException("%d rows were deleted but 1 was expected only", rows);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public Ski findSkiById(int id) {
        Ski ski = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ski = map(resultSet);
                if (resultSet.next()) {
                    throw new ApplicationException("More than one row was selected but 1 was expected only");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
        return ski;
    }

    @Override
    public List<Ski> findAllSki() {
        List<Ski> result = new ArrayList<Ski>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                result.add(map(resultSet));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } finally {
            close(statement, resultSet);
        }
        return result;
    }

    public void createSkiTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            throw new ApplicationException("SKI table was not created", e);
        } finally {
            close(statement, null);
        }
    }

    public Ski map(ResultSet resultSet) throws SQLException {
        Ski result = new Ski();
        result.setId(resultSet.getInt("SKI_ID"));
        result.setBrand(resultSet.getString("BRAND"));
        result.setName(resultSet.getString("NAME"));
        result.setArticle(resultSet.getString("ARTICLE"));
        result.setDescription(resultSet.getString("DESCRIPTION"));
        result.setPrice(resultSet.getBigDecimal("PRICE"));
        return result;
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
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
