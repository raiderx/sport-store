package org.karpukhin.sportstore.web;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.dao.SkiBootDao;
import org.karpukhin.sportstore.core.model.SkiBoot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pavel Karpukhin
 * @since 19.10.12
 */
public class SkiBootServlet extends HttpServlet {

    public static final String ACTION_PARAM = "action";
    public static final String ID_PARAM = "id";
    public static final String BRAND_PARAM = "brand";
    public static final String NAME_PARAM = "name";
    public static final String ARTICLE_PARAM = "article";
    public static final String FLEX_INDEX_PARAM = "flexIndex";
    public static final String MIN_SIZE_PARAM = "minSize";
    public static final String MAX_SIZE_PARAM = "maxSize";
    public static final String DESCRIPTION_PARAM = "description";
    public static final String COLOR_PARAM = "color";
    public static final String PRICE_PARAM = "price";

    public static final String MODEL_ATTR = "model";

    public static final String CREATE_ACTION = "create";
    public static final String EDIT_ACTION = "edit";
    public static final String REMOVE_ACTION = "remove";

    private final Logger logger = Logger.getLogger(getClass().getName());

    private SkiBootDao skiBootDao;

    @Override
    public void init() throws ServletException {
        skiBootDao = (SkiBootDao) getServletContext().getAttribute("skiBootDao");
        if (skiBootDao == null) {
            logger.log(Level.SEVERE, "Bean 'skiBootDao' was not found");
            throw new ApplicationException("Bean 'skiBootDao' was not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION_PARAM);
        if (CREATE_ACTION.equals(action)) {
            create(request, response);
        } else if (EDIT_ACTION.equals(action)) {
            edit(request, response);
        } else if (REMOVE_ACTION.equals(action)) {
            remove(request, response);
        } else if (action != null && !action.isEmpty()) {
            response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
        } else {
            list(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String action = request.getParameter(ACTION_PARAM);

        if (CREATE_ACTION.equals(action)) {
            SkiBoot skiBoot = extractModel(request);
            skiBootDao.createSkiBoot(skiBoot);
        } else if (EDIT_ACTION.equals(action)) {
            SkiBoot skiBoot = extractModel(request);
            skiBootDao.updateSkiBoot(skiBoot);
        } else if (REMOVE_ACTION.equals(action)) {
            SkiBoot skiBoot = extractModel(request);
            skiBootDao.removeSkiBootById(skiBoot.getId());
        }
        response.sendRedirect(String.format("%s%s?action=list", request.getContextPath(), request.getServletPath()));
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SkiBoot> list = skiBootDao.findAllSkiBoots();
        request.setAttribute(MODEL_ATTR, list);
        request.getRequestDispatcher("/WEB-INF/views/skiBoot/list.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MODEL_ATTR, new SkiBoot());
        request.getRequestDispatcher("/WEB-INF/views/skiBoot/createEdit.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID_PARAM));
        SkiBoot skiBoot = skiBootDao.findSkiBootById(id);
        request.setAttribute(MODEL_ATTR, skiBoot);
        request.getRequestDispatcher("/WEB-INF/views/skiBoot/createEdit.jsp").forward(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID_PARAM));
        SkiBoot skiBoot = skiBootDao.findSkiBootById(id);
        request.setAttribute(MODEL_ATTR, skiBoot);
        request.getRequestDispatcher("/WEB-INF/views/skiBoot/remove.jsp").forward(request, response);
    }

    public SkiBoot extractModel(HttpServletRequest request) {
        SkiBoot result = new SkiBoot();

        String id = request.getParameter(ID_PARAM);
        String brand = request.getParameter(BRAND_PARAM);
        String name = request.getParameter(NAME_PARAM);
        String article = request.getParameter(ARTICLE_PARAM);
        String flexIndex = request.getParameter(FLEX_INDEX_PARAM);
        String minSize = request.getParameter(MIN_SIZE_PARAM);
        String maxSize = request.getParameter(MAX_SIZE_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM);
        String color = request.getParameter(COLOR_PARAM);
        String price = request.getParameter(PRICE_PARAM);

        if (id != null && !id.trim().isEmpty()) {
            result.setId(Integer.parseInt(id.trim()));
        }
        if (brand != null && !brand.trim().isEmpty()) {
            result.setBrand(brand.trim());
        }
        if (name != null && !name.trim().isEmpty()) {
            result.setName(name.trim());
        }
        if (article != null && !article.trim().isEmpty()) {
            result.setArticle(article.trim());
        }
        if (flexIndex != null && !flexIndex.trim().isEmpty()) {
            result.setFlexIndex(Integer.parseInt(flexIndex.trim()));
        }
        if (minSize != null && !minSize.trim().isEmpty()) {
            result.setMinSize(new BigDecimal(minSize.trim()));
        }
        if (maxSize != null && !maxSize.trim().isEmpty()) {
            result.setMaxSize(new BigDecimal(maxSize.trim()));
        }
        if (description != null && !description.trim().isEmpty()) {
            result.setDescription(description.trim());
        }
        if (color != null && !color.trim().isEmpty()) {
            result.setColor(color.trim());
        }
        if (price != null && !price.trim().isEmpty()) {
            result.setPrice(new BigDecimal(price.trim()));
        }
        return result;
    }
}
