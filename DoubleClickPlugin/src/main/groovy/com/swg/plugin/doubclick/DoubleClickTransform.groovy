package com.swg.plugin.doubclick

import com.android.build.api.transform.TransformInvocation
import com.swg.plugin.BaseTransform
import com.swg.plugin.LambdaInsnNode
import com.swg.plugin.doubclick.DoubleClickConfig
import com.swg.plugin.doubclick.visitor.DoubleClickClassVisitor
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

class DoubleClickTransform extends BaseTransform {

    /**
     * 要检测的注解
     */
    static String checkAnnotation

    static String checkAnnotationName

    DoubleClickTransform(Project project) {
        super(project)
    }

    @Override
    protected boolean shouldHookClassInner(String className) {
        println "shouldHookClassInner className = " + className
        return true
    }

    @Override
    protected byte[] hookClassInner(String className, byte[] bytes) {
        println "hookClassInner className = " + className
        List<LambdaInsnNode> lambdaInsnNodeList = new ArrayList<>()
        ClassReader cr = new ClassReader(bytes)
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS)
        ClassVisitor classVisitor = new DoubleClickClassVisitor(Opcodes.ASM9, cw, lambdaInsnNodeList)
        cr.accept(classVisitor, ClassReader.EXPAND_FRAMES)
        ClassNode classNode = new ClassNode()
        cr = new ClassReader(cw.toByteArray())
        cr.accept(classNode, ClassReader.EXPAND_FRAMES)
        if (!lambdaInsnNodeList.isEmpty()) {
            classNode.methods.each { methodNode ->
                def argumentTypes = Type.getArgumentTypes(methodNode.desc)
                def viewParamIndex = viewParamIndex(argumentTypes, DoubleClickConfig.ViewDescriptor)
                insertLambda(cr.className, methodNode, viewParamIndex, argumentTypes, lambdaInsnNodeList)
            }
        }
        def classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS)
        classNode.accept(classWriter)
        return classWriter.toByteArray()
    }

    private static int viewParamIndex(types, viewDescriptor) {
        return types.findIndexOf { type ->
            type.getDescriptor() == viewDescriptor
        }
    }

    private static void insertLambda(className, methodNode, viewParamIndex, argumentTypes, lambdaInsnNodeList) {
        println 'insertLambda className = ' + className
    }

    @Override
    protected void onTransformStart(TransformInvocation transformInvocation) {

    }

    @Override
    protected void onTransformEnd(TransformInvocation transformInvocation) {

    }
}