package org.karpukhin.sportstore.core.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.*;
import org.karpukhin.sportstore.core.dao.SkiDao;
import org.karpukhin.sportstore.core.dao.SkiDaoImpl;
import org.karpukhin.sportstore.core.model.Ski;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Pavel Karpukhin
 * @since 15.10.12
 */
public class SkiDaoImplTest {

    public static final String JDBC_URL = "jdbc:derby:memory:sport-store;create=true";
	public static final String SQL_INSERT_SKI =
			"INSERT INTO SKI (BRAND, NAME, ARTICLE, DESCRIPTION, PRICE) " +
			"VALUES ('Test', 'Test', 'Test', 'Test', 12.3)";
	public static final String SQL_COUNT_SKI =
			"SELECT COUNT(*) FROM SKI";

	private static Connection connection;
    private static SkiDao skiDao;

	private QueryRunner runner = new QueryRunner();
    private int rows;

	@BeforeClass
    public static void testSetUp() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        SkiDaoImpl skiDaoImpl = new SkiDaoImpl(connection);
		skiDaoImpl.createSkiTable();
        skiDao = skiDaoImpl;
    }

    @Before
    public void setUp() {
        try {
            connection.setAutoCommit(false);
			runner.update(connection, SQL_INSERT_SKI);
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
    public void testCreateSki() {
        Ski ski = new Ski("Some brand", "Some ski", "Some article", "Some description", BigDecimal.valueOf(12.3));
        skiDao.createSki(ski);
        assertNotSame(0, ski.getId());
		assertEquals(rows + 1, countRows());
	}

	@Test
	public void testUpdateSki() {
		Ski ski = new Ski("Some brand", "Some ski", "Some article", "Some description", BigDecimal.valueOf(12.3));
		ski.setId(3);
		skiDao.updateSki(ski);
		skiDao.findSkiById(3);
		assertEquals(ski.getBrand(), "Some brand");
		assertEquals(rows, countRows());
	}

	@Test
	public void testRemoveSkiById() {
		skiDao.removeSkiById(4);
		List<Ski> result = skiDao.findAllSki();
		assertEquals(0, result.size());
		assertEquals(rows - 1, countRows());
	}

	@Test
	public void testFindSkiById() {
		Ski ski = skiDao.findSkiById(5);
		assertNotNull(ski);
		assertEquals(ski.getBrand(), "Test");
	}

	@Test
	public void testFindAllSki() {
		List<Ski> result = skiDao.findAllSki();
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
