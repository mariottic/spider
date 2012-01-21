package com.nbb.spider.manager.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;
import com.nbb.spider.manager.exporter.impl.BaiduExporter;
import com.nbb.spider.manager.exporter.impl.QiyiExporter;
import com.nbb.spider.manager.exporter.impl.QiyiTop50Exporter;
import com.nbb.spider.manager.webspider.YoukuExporter;
import com.nbb.spider.manager.webspider.YoukuSpider;
import com.nbb.spider.manager.webspider.impl.BaiduSpider;
import com.nbb.spider.manager.webspider.impl.QiyiSpider;
import com.nbb.spider.manager.webspider.impl.QiyiTop50Spider;

public class SpiderTask {
	private final static String KEY = "spided";
	private final static String EXTENSION = "xls";
	private String destination = "/tmp/";

	public void run() throws IOException {
		QiyiExporter export = new QiyiExporter();
		Workbook wb = export.createWorkBook();
		qiyi(wb);
		baidu(wb);
		youku(wb);
		export.saveWorkBook(wb, getDestinationFullPathWithEncoding());
	}

	protected String getDestinationFullPathWithEncoding() {
		StringBuffer sb = new StringBuffer();
		Long current = System.currentTimeMillis();
		int code = (int) (current % 10000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = sdf.format(new Date());
		sb.append(this.destination).append(KEY).append(".")
				.append(yyyyMMdd).append(".").append(code).append(".")
				.append(EXTENSION);
		return sb.toString();
	}

	protected void qiyi(Workbook wb) throws IOException {
		QiyiExporter export = new QiyiExporter();
		QiyiSpider spider = new QiyiSpider();
		List<? extends Item> oldQiyiRank1 = spider.extract();
		QiyiTop50Spider t50Spider = new QiyiTop50Spider();
		t50Spider.setTargetUrl(QiyiTop50Spider.QIYIURL_MOVIE);
		List<? extends Item> qiyiMovie = t50Spider.extract();
		QiyiTop50Exporter t50Export = new QiyiTop50Exporter();
		t50Export.export(wb, qiyiMovie, "qiyiMoive");
		t50Spider.setTargetUrl(QiyiTop50Spider.QIYIURL_TV);
		List<? extends Item> qiyiTV = t50Spider.extract();
		t50Export.export(wb, qiyiTV, "qiyiTV");
		List<? extends Item> oldQiyiRank = oldQiyiRank1;
		export.export(wb, oldQiyiRank, "oldQiyis");
	}

	protected void baidu(Workbook wb) throws IOException {
		BaiduSpider spider = new BaiduSpider();
		spider.setTargetUrl(BaiduSpider.BUIDU_MOVIE);
		List<? extends Item> movieItems = spider.extract();
		BaiduExporter export = new BaiduExporter();
		export.export(wb, movieItems, "baiduMovie");
		spider.setTargetUrl(BaiduSpider.BUIDU_TV);
		List<? extends Item> tvItems = spider.extract();
		export.export(wb, tvItems, "baiduTV");
	}

	protected void youku(Workbook wb) throws IOException {
		YoukuSpider spider = new YoukuSpider();
		spider.setTargetUrl(YoukuSpider.YOUKU_MOVIE);
		List<? extends Item> movieItems = spider.extract();
		YoukuExporter export = new YoukuExporter();
		export.export(wb, movieItems, "youkuMovie");
		spider.setTargetUrl(YoukuSpider.YOUKU_TV);
		List<? extends Item> tvItems = spider.extract();
		export.export(wb, tvItems, "youkuTV");
	}

	public static void main(String[] args) throws IOException {
		SpiderTask spiderTask = new SpiderTask();
		spiderTask.run();
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
