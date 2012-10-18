package org.karpukhin.sportstore.web;

import org.karpukhin.sportstore.core.ApplicationException;
import org.karpukhin.sportstore.core.model.Ski;
import org.karpukhin.sportstore.core.dao.SkiDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class SkiServlet extends HttpServlet {

	public static final String ACTION_PARAM = "action";
	public static final String ID_PARAM = "id";
	public static final String BRAND_PARAM = "brand";
	public static final String NAME_PARAM = "name";
	public static final String ARTICLE_PARAM = "article";
	public static final String DESCRIPTION_PARAM = "description";
	public static final String PRICE_PARAM = "price";

	public static final String MODEL_ATTR = "model";

	public static final String CREATE_ACTION = "create";
	public static final String EDIT_ACTION = "edit";
	public static final String REMOVE_ACTION = "remove";

	private SkiDao skiDao;

	@Override
	public void init() throws ServletException {
        skiDao = (SkiDao)getServletContext().getAttribute("skiDao");
        if (skiDao == null) {
			log("Bean 'skiDao' was not found");
			throw new ApplicationException("Bean 'skiDao' was not found");
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
			Ski ski = extractModel(request);
			skiDao.createSki(ski);
		} else if (EDIT_ACTION.equals(action)) {
			Ski ski = extractModel(request);
			skiDao.updateSki(ski);
		} else if (REMOVE_ACTION.equals(action)) {
			Ski ski = extractModel(request);
			skiDao.removeSkiById(ski.getId());
		}
		response.sendRedirect(String.format("%s%s?action=list", request.getContextPath(), request.getServletPath()));
	}

    public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Ski> list = skiDao.findAllSki();
		request.setAttribute(MODEL_ATTR, list);
		request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(MODEL_ATTR, new Ski());
		request.getRequestDispatcher("/WEB-INF/views/createEdit.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(ID_PARAM));
		Ski ski = skiDao.findSkiById(id);
		request.setAttribute(MODEL_ATTR, ski);
		request.getRequestDispatcher("/WEB-INF/views/createEdit.jsp").forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(ID_PARAM));
		skiDao.removeSkiById(id);
		response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
	}

	public Ski extractModel(HttpServletRequest request) {
		Ski result = new Ski();

		String id = request.getParameter(ID_PARAM);
		String brand = request.getParameter(BRAND_PARAM);
		String name = request.getParameter(NAME_PARAM);
		String article = request.getParameter(ARTICLE_PARAM);
		String description = request.getParameter(DESCRIPTION_PARAM);
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
		if (description != null && !description.trim().isEmpty()) {
			result.setDescription(description.trim());
		}
		if (price != null && !price.trim().isEmpty()) {
			result.setPrice(new BigDecimal(price.trim()));
		}
		return result;
	}
}
