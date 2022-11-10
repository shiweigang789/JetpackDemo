package com.mi.plugin.doubleClick

import org.gradle.api.Plugin
import org.gradle.api.Project

class ViewDoubleClickPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("ViewDoubleClickPlugin" + project.plugins)
    }

}