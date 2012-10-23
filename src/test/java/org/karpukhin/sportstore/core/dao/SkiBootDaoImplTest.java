package org.karpukhin.sportstore.core.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.*;
import org.karpukhin.sportstore.core.model.SkiBoot;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Pavel Karpukhin
 */
public class SkiBootDaoImplTest {

    public static final String JDBC_URL = "jdbc:derby:memory:sport-store;create=true";
    public static final String SQL_INSERT_SKI_BOOT =
            "INSERT INTO SKI_BOOT (BRAND, NAME, ARTICLE, FLEX_INDEX, MIN_SIZE, MAX_SIZE, DESCRIPTION, COLOR, PRICE) " +
            "VALUES ('Test', 'Test', 'Test', 1, 37.5, 42, 'Test', 'Test', 99.9)";
    public static final String SQL_COUNT_SKI =
            "SELECT COUNT(*) FROM SKI_BOOT";

    private static Connection connection;
    private static SkiBootDao skiBootDao;

    private QueryRunner runner = new QueryRunner();
    private int rows;

    @BeforeClass
    public static void testSetUp() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        SkiBootDaoImpl skiBootDaoImpl = new SkiBootDaoImpl(connection);
        skiBootDaoImpl.createSkiBootTable();
        skiBootDao = skiBootDaoImpl;
    }

    @Before
    public void setUp() {
        try {
            connection.setAutoCommit(false);
            runner.update(connection, SQL_INSERT_SKI_BOOT);
            rows = countRows();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @After
    public void tearDown() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void testTearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateSkiBoot() {
        SkiBoot skiBoot = new SkiBoot("Some brand", "Some ski", "Some article", 1, BigDecimal.valueOf(36), BigDecimal.valueOf(42.5), "Some description", "Some color", BigDecimal.valueOf(12.3));
        skiBootDao.createSkiBoot(skiBoot);
        assertNotSame(0, skiBoot.getId());
        assertEquals(rows + 1, countRows());
    }

    @Test
    public void testUpdateSkiBoot() {
        SkiBoot skiBoot = new SkiBoot("Some brand", "Some ski", "Some article", 1, BigDecimal.valueOf(36), BigDecimal.valueOf(42.5), "Some description", "Some color", BigDecimal.valueOf(12.3));
        skiBoot.setId(3);
        skiBootDao.updateSkiBoot(skiBoot);
        skiBootDao.findSkiBootById(3);
        assertEquals(skiBoot.getBrand(), "Some brand");
        assertEquals(rows, countRows());
    }

    @Test
    public void testRemoveSkiBootById() {
        skiBootDao.removeSkiBootById(4);
        List<SkiBoot> result = skiBootDao.findAllSkiBoots();
        assertEquals(0, result.size());
        assertEquals(rows - 1, countRows());
    }

    @Test
    public void testFindSkiBootById() {
        SkiBoot skiBoot = skiBootDao.findSkiBootById(5);
        assertNotNull(skiBoot);
        assertEquals(skiBoot.getBrand(), "Test");
    }

    @Test
    public void testFindAllSkiBoots() {
        List<SkiBoot> result = skiBootDao.findAllSkiBoots();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    private int countRows() {
        int res = -1;
        try {
            res = runner.query(connection, SQL_COUNT_SKI, new ScalarHandler<Integer>());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
        return res;
    }
}
