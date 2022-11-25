package com.swg.plugin.doubclick

import com.android.build.gradle.AppExtension
import com.swg.plugin.util.CommonUtil
import org.gradle.api.Plugin
import org.gradle.api.Project

class DoubleClickPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "-----------> project.name=${project.name}"
        project.extensions.create('double_click_config', DoubleClickConfig)
        AppExtension android = project.extensions.getByType(AppExtension.class)
        android.registerTransform(new DoubleClickTransform(project))

        project.afterEvaluate {
            DoubleClickTransform.checkAnnotation = CommonUtil.formatName(project.double_click_config.checkAnnotation)
            println "DoubleClickTransform.checkAnnotation = ${DoubleClickTransform.checkAnnotation}"
            DoubleClickTransform.checkAnnotationName =project.double_click_config.annotationName
            println "DoubleClickTransform.checkAnnotationName = ${DoubleClickTransform.checkAnnotationName}"
        }
    }

}