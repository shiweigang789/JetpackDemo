package com.swg.easeupload

import org.gradle.api.Plugin
import org.gradle.api.Project

class EasyPrivacyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println('EasyPrivacyPlugin')
    }
}