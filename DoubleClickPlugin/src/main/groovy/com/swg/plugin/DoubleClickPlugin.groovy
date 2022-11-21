package com.swg.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class DoubleClickPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "-----------> project.name=${project.name}"
        project.extensions.create('double_click_config', DoubleClickConfig)
        AppExtension android = project.extensions.getByType(AppExtension.class)
        android.registerTransform(new DoubleClickTransform(project))
    }

}