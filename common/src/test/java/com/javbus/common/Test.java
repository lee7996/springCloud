package com.javbus.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.javbus.common.utils.ReadFilesRecursion;
import com.javbus.common.utils.XmlToJson;

import io.netty.util.internal.StringUtil;
import net.sf.json.JSON;

public class Test {

	public static void main(String[] args) throws JSONException {
//		List<ResaKeys> list = new ArrayList<>();
//		ResaKeys rk = new ResaKeys();
//		list.add(rk);
//		Object rk2 = list.get(0);
//		list.get(0).
		List<File> files = new ArrayList<>();
		String path = "D:\\server\\GameRes\\resource\\script\\talk_npc";
		ReadFilesRecursion.readFiles(path, files);
		List<String> fileNameList = new ArrayList<>();
		for (File file : files) {
			String fileName = file.getName();
			String[] nameArr = fileName.split("\\.");
			if (nameArr.length > 1) {
				System.out.println(">>>>>  解析文件: " + fileName);
				String fileType = nameArr[nameArr.length - 1].toUpperCase();
				if ("XML".equals(fileType)) {
					try {
						JSONObject json = XmlToJson.xmlToJson(file);
						System.out.println(json);
//						JSONObject obj = JSONObject.parseObject(json.toString());
						if (json.has("quest") && json.getJSONObject("quest").has("talk_paragraph")) {
							JSONArray jsons = json.getJSONObject("quest").getJSONArray("talk_paragraph");
							for (int i = 0; i < jsons.length(); i++) {
								JSONObject currentJson = jsons.getJSONObject(i);
//								if (currentJson.getJSONObject("talk_question") != null && currentJson.getJSONObject("talk_question").getString("content").indexOf("在50年前的黑龙之突袭时获得了") != -1) {
								if (currentJson.has("talk_question")) {
									if (currentJson.getJSONObject("talk_question").has("content")) {
										String str = currentJson.getJSONObject("talk_question").get("content").toString();
										 if (str.indexOf("就在要搀扶起少女的瞬间") != -1) {
											 System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
											 fileNameList.add(file.getName());
										 }
									}
								}
//								if (currentJson.has("talk_question") && currentJson.getJSONObject("talk_question").has("content")) {
//									String str = currentJson.getJSONObject("talk_question").getJSONObject("content").toString();
//									System.out.println(">>>>>>>>>>>>>>>>>>current:" + str);
//									if (str.indexOf("在50年前的黑龙之突袭时获得了") != -1) {
//										System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
//										fileNameList.add(file.getName());
//									}
//								}
							}
						} else if (json.has("talk") && json.getJSONObject("talk").has("talk_paragraph")) {
							try {
								JSONArray jsons = json.getJSONObject("talk").getJSONArray("talk_paragraph");
								for (int i = 0; i < jsons.length(); i++) {
									JSONObject currentJson = jsons.getJSONObject(i);
//									if (currentJson.getJSONObject("talk_question") != null && currentJson.getJSONObject("talk_question").getString("content").indexOf("在50年前的黑龙之突袭时获得了") != -1) {
									if (currentJson.has("talk_question")) {
										if (currentJson.getJSONObject("talk_question").has("content")) {
											String str = currentJson.getJSONObject("talk_question").get("content").toString();
											 if (str.indexOf("在50年前的黑龙之突袭时获得了") != -1) {
												 System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
												 fileNameList.add(file.getName());
											 }
										}
									}
//									if (currentJson.has("talk_question") && currentJson.getJSONObject("talk_question").has("content")) {
//										String str = currentJson.getJSONObject("talk_question").getJSONObject("content").toString();
//										System.out.println(">>>>>>>>>>>>>>>>>>current:" + str);
//										if (str.indexOf("在50年前的黑龙之突袭时获得了") != -1) {
//											System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
//											fileNameList.add(file.getName());
//										}
//									}
								}
							} catch (Exception e) {
								JSONObject currentJson = json.getJSONObject("talk").getJSONObject("talk_paragraph");
//								if (currentJson.getJSONObject("talk_question") != null && currentJson.getJSONObject("talk_question").getString("content").indexOf("在50年前的黑龙之突袭时获得了") != -1) {
								if (currentJson.has("talk_question")) {
									if (currentJson.getJSONObject("talk_question").has("content")) {
										String str = currentJson.getJSONObject("talk_question").get("content").toString();
										 if (str.indexOf("在50年前的黑龙之突袭时获得了") != -1) {
											 System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
											 fileNameList.add(file.getName());
										 }
									}
								}
//									if (currentJson.has("talk_question") && currentJson.getJSONObject("talk_question").has("content")) {
//										String str = currentJson.getJSONObject("talk_question").getJSONObject("content").toString();
//										System.out.println(">>>>>>>>>>>>>>>>>>current:" + str);
//										if (str.indexOf("在50年前的黑龙之突袭时获得了") != -1) {
//											System.out.println(">>>>>>>>>>>>>>>  收入成功：" + file.getName());
//											fileNameList.add(file.getName());
//										}
//								}
							}
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(">>>>>>>>>>>>>>>>>>>> " + file.getName() + "解析异常!!!");
					}
				}
			}
		}
		System.out.println(fileNameList);
	}
}
