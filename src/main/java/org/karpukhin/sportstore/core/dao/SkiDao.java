package org.karpukhin.sportstore.core.dao;

import org.karpukhin.sportstore.core.model.Ski;

import java.util.List;

/**
 * This interface describes CRUD operations for {@link Ski} entity
 * @author Pavel Karpukhin
 * @since 12.10.12
 */
public interface SkiDao {

	/**
	 * Creates new ski with given data or throws {@link ApplicationException} if no rows were inserted
	 * @param ski data of ski to be created
	 * @throws ApplicationException if no rows were inserted
	 */
    void createSki(Ski ski);

	/**
	 * Updates existing ski by given data or throws {@link ApplicationException} if no rows were updated
	 * @param ski ski data to be updated
	 * @throws ApplicationException if no rows were updated
	 */
    void updateSki(Ski ski);

	/**
	 * Removes ski with given unique id or throws {@link ApplicationException} if no rows were removed
	 * @param id unique id of ski
	 * @throws ApplicationException if no rows were removed
	 */
    void removeSkiById(int id);

	/**
	 * Returns ski found by given unique id or {@code null} otherwise
	 * @param id unique id of ski
	 * @return ski found by given unique id or {@code null} otherwise
	 */
    Ski findSkiById(int id);

	/**
	 * Returns entire list of ski
	 * @return entire list of ski
	 */
    List<Ski> findAllSki();
}
