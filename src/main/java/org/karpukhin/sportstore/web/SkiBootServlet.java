package org.karpukhin.sportstore.web;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.dao.SkiBootDao;
import org.karpukhin.sportstore.core.model.SkiBoot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 19.10.12
 */
public class SkiBootServlet extends HttpServlet {

    public static final String ACTION_PARAM = "action";

    public static final String MODEL_ATTR = "model";

    public static final String CREATE_ACTION = "create";
    public static final String EDIT_ACTION = "edit";
    public static final String REMOVE_ACTION = "remove";

    private SkiBootDao skiBootDao;

    @Override
    public void init() throws ServletException {
        skiBootDao = (SkiBootDao) getServletContext().getAttribute("skiBootDao");
        if (skiBootDao == null) {
            log("Bean 'skiBootDao' was not found");
            throw new ApplicationException("Bean 'skiBootDao' was not found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION_PARAM);
        if (CREATE_ACTION.equals(action)) {
            //create(request, response);
        } else if (EDIT_ACTION.equals(action)) {
            //edit(request, response);
        } else if (REMOVE_ACTION.equals(action)) {
            //remove(request, response);
        } else if (action != null && !action.isEmpty()) {
            response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
        } else {
            list(request, response);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SkiBoot> list = skiBootDao.findAllSkiBoots();
        request.setAttribute(MODEL_ATTR, list);
        request.getRequestDispatcher("/WEB-INF/views/skiBoot/list.jsp").forward(request, response);
    }
}
