package com.zfb.bootworld.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class XmloverridePluginAdapter extends PluginAdapter {

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		sqlMap.setMergeable(false);
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	@Override
	public boolean validate(List<String> list) {
		return true;
	}
}
