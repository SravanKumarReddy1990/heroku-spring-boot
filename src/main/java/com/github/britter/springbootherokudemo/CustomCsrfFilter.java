/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.britter.springbootherokudemo;


import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
 
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
public class CustomCsrfFilter extends OncePerRequestFilter {
 
	public static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
 
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
 
		if (csrf != null) {
 
			Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
			String token = csrf.getToken();
 
			if (cookie == null || token != null && !token.equals(cookie.getValue())) {
				cookie = new Cookie(CSRF_COOKIE_NAME, token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
 
		filterChain.doFilter(request, response);
	}
}
