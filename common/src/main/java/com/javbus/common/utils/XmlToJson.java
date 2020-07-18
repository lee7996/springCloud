package com.javbus.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;





public class XmlToJson {

	public static JSONObject xmlToJson(File file) throws IOException {
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			String xml = IOUtils.toString(input);
//			XMLSerializer xmlSerializer = new XMLSerializer();
//			JSON json = xmlSerializer.read(xml);
			JSONObject json = XML.toJSONObject(xml);
			return json;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
}
