package org.example.my;

import java.util.Map;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyRepositoryRegistar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
        BeanDefinitionRegistry registry) {

        // 주입할 빈에 대해 프로그래밍 하는 부분
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyRepository.class);
        beanDefinition.getPropertyValues().add("dataTable", Map.of(1L, "data"));

        registry.registerBeanDefinition("myRepository", beanDefinition);
    }
}
