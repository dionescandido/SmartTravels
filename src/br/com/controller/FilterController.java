package br.com.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FilterController implements Filter{
	
	
	
public static final String loginPage = "Login.xhtml";
public static final String indexPage = "Index.xhtml";



   public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain filterChain) throws IOException, ServletException {

           System.out.println("Filtrando");

       if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {

           HttpServletRequest httpServletRequest = (HttpServletRequest) request;
           HttpServletResponse httpServletResponse = (HttpServletResponse) response;


           if (isSessionInvalid(httpServletRequest)) {
                 String timeoutUrl = httpServletRequest.getContextPath() + "/" + loginPage;
                 httpServletResponse.sendRedirect(timeoutUrl);
                 return;
           }

       }

       filterChain.doFilter(request, response);

   }   

   private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {

       boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
               && !httpServletRequest.isRequestedSessionIdValid();

       return sessionInValid;

   }

   public void init(FilterConfig filterConfig) throws ServletException {
     System.out.println("Inicicando Sessão");
   }

   public void destroy() {
      System.out.println("Sessão destruida!");
   }
   
   
}
