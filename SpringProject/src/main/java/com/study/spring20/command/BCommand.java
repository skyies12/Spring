package com.study.spring20.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BCommand {

	public void execute(HttpServletRequest request, Model model);
	
} 
