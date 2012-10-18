package org.karpukhin.sportstore.core.dao;

import org.karpukhin.sportstore.core.model.SkiBoot;

import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 18.10.12
 */
public interface SkiBootDao {

    void createSkiBoot(SkiBoot skiBoot);

    void updateSkiBoot(SkiBoot skiBoot);

    void removeSkiBootById(int id);

    SkiBoot findSkiBootById(int id);

    List<SkiBoot> findAllSkiBoots();
}
