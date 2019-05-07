package com.study.spring20;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface Service {
	void execute(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException;
}
