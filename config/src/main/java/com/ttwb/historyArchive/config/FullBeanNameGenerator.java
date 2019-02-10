package com.ttwb.historyArchive.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Created by user on 2017/1/19.
 */
public class FullBeanNameGenerator extends AnnotationBeanNameGenerator {
    //判断是否是否是AnnotatedBeanDefinition的子类， AnnotatedBeanDefinition是BeanDefinition的一个子类
    //如果是AnnotatedBeanDefinition ， 按照注解生成模式生成信息，否则生成默认的bean name
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        return definition.getBeanClassName();
    }

}
