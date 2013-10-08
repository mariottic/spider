package com.nbb.spider.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nbb.spider.dao.FullItemDao;
import com.nbb.spider.entity.full.FullItem;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private FullItemDao itemDao;

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@Transactional
	public void export(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"spider.csv\"");
		PrintWriter out = response.getWriter();
		out.println(FullItem.csvHeader());
		List<FullItem> list = itemDao.all();
		for (FullItem fi : list) {
			out.println(fi.toCsv());
		}
		out.close();
	}

	@RequestMapping(value = "/report/{company}/{type}/{contenttype}", method = RequestMethod.GET)
	public void report(@PathVariable Integer company,
			@PathVariable Integer type, @PathVariable String contenttype) {
		System.out.println(company + type + contenttype);
	}
}