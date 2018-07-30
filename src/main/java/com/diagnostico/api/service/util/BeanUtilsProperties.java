package com.diagnostico.api.service.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtilsProperties {
	
	public static String[] getNullPropertyNames (Object source, String ...ignoredProperties) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> ignoredNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) ignoredNames.add(pd.getName());
	    }
	    
	    if(ignoredProperties.length > 0) {
	    	for(String property : ignoredProperties) {
	    		ignoredNames.add(property);
	    	}
	    }
	    
	    String[] result = new String[ignoredNames.size()];
	    return ignoredNames.toArray(result);
	}

	public static void myCopyProperties(Object src, Object target, String ... ignoredProperties) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src, ignoredProperties));
	}
	
}
