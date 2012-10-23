package org.karpukhin.sportstore.core.dao;

import org.karpukhin.sportstore.core.model.SkiBoot;

import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 18.10.12
 */
public interface SkiBootDao {

    /**
     * Creates new ski boot with given data or throws {@link ApplicationException} if no rows were inserted
     * @param skiBoot data of ski boot to be created
     * @throws ApplicationException if no rows were inserted
     */
    void createSkiBoot(SkiBoot skiBoot);

    /**
     * Updates existing ski boot by given data or throws {@link ApplicationException} if no rows were updated
     * @param skiBoot ski boot data to be updated
     * @throws ApplicationException if no rows were updated
     */
    void updateSkiBoot(SkiBoot skiBoot);

    /**
     * Removes ski boot with given unique id or throws {@link ApplicationException} if no rows were removed
     * @param id unique id of ski boot
     * @throws ApplicationException if no rows were removed
     */
    void removeSkiBootById(int id);

    /**
     * Returns ski boot found by given unique id or {@code null} otherwise
     * @param id unique id of ski boot
     * @return ski boot found by given unique id or {@code null} otherwise
     */
    SkiBoot findSkiBootById(int id);

    /**
     * Returns entire list of ski boots
     * @return entire list of ski boots
     */
    List<SkiBoot> findAllSkiBoots();
}
