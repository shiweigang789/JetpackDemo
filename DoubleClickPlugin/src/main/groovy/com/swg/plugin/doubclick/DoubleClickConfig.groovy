package com.swg.plugin.doubclick

import com.swg.plugin.util.CommonUtil

class DoubleClickConfig {
    public static String ViewDescriptor = "Landroid/view/View;"

    /**
     * 处理快速点击的工具类
     */
    public static String doubleClickCheckClass = CommonUtil.getClassInternalName("com.zhangyue.ireader.toolslibrary.doubleclick.DoubleClickConfig")
    public static String doubleClickCheckMethod = "inDoubleClick"
    public static String doubleClickCheckMethodDesc = "(Ljava/lang/Object;J)Z"
    public static int doubleClickCheckDuration = 800

    public static String clickLambdaName = "onClick"
    public static String clickLambdaInterfaces = "Landroid/view/View\$OnClickListener;"

    String checkAnnotation

    String annotationName


}