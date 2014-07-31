package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

public abstract class AbstractFinnalServlet extends HttpServlet {

    public void render(HttpServletRequest request, HttpServletResponse response, String page, Map<String,Object> dataModel)
        throws IOException, ServletException {
    for(String key : dataModel.keySet()) {
        request.setAttribute(key,dataModel.get(key));
    }
    
    getServletContext()
        .getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp")
        .forward(request,response);
    }  
    
}