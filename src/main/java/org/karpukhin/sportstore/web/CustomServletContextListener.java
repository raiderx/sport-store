package org.karpukhin.sportstore.web;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.dao.SkiBootDaoImpl;
import org.karpukhin.sportstore.core.dao.SkiDao;
import org.karpukhin.sportstore.core.dao.SkiDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pavel Karpukhin
 * @since 17.10.12
 */
public class CustomServletContextListener implements ServletContextListener {

    public static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String JDBC_URL = "jdbc:derby://localhost/sport-store";

    public static final String CONNECTION_ATTR = "connection";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL);
            ServletContext context = sce.getServletContext();
            context.setAttribute(CONNECTION_ATTR, connection);
            context.setAttribute("skiDao", new SkiDaoImpl(connection));
            context.setAttribute("skiBootDao", new SkiBootDaoImpl(connection));
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection)sce.getServletContext().getAttribute(CONNECTION_ATTR);
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver(JDBC_URL));
        } catch (SQLException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
