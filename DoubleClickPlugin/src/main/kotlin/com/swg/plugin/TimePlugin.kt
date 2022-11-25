package com.swg.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class TimePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("TimePlugin apply")
    }

}