package com.swg.timeprint.asm

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import com.swg.timeprint.bean.ScanBean
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldInsnNode
import org.objectweb.asm.tree.MethodInsnNode

interface ScanParams : InstrumentationParameters {
    @get:Input
    val ignoreOwner: Property<String>

    @get:Input
    val listOfScans: ListProperty<ScanBean>
}

abstract class ScanClassVisitorFactory : AsmClassVisitorFactory<ScanParams> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return ScanClassNode(nextClassVisitor, parameters.get().listOfScans.get())
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return !classData.className.startsWith(parameters.get().ignoreOwner.get().replace("/", "."))
    }

}

class ScanClassNode(private val classVisitor: ClassVisitor, private val scans: List<ScanBean>) :
    ClassNode(Opcodes.ASM9) {

    override fun visitEnd() {
        methods.forEach { methodNode ->
            println("methodNode ----------------------------->")
            println("methodNode ${methodNode.access}")
            println("methodNode ${methodNode.name}")
            println("methodNode ${methodNode.desc}")
            println("methodNode ${methodNode.instructions}")
            println("methodNode ${methodNode.signature}")
            println("methodNode ${methodNode.exceptions}")
            val instructions = methodNode.instructions
            val iterator = instructions.iterator()
            while (iterator.hasNext()) {
                val insnNode = iterator.next()
                if (insnNode is FieldInsnNode) {
                    println("insnNode FieldInsnNode")
                    println("insnNode ${insnNode.name}")
                    println("insnNode ${insnNode.desc}")
                    println("insnNode ${insnNode.owner}")
                    println("insnNode ${insnNode.opcode}")
                }
                if (insnNode is MethodInsnNode) {
                    println("insnNode MethodInsnNode")
                    println("insnNode ${insnNode.name}")
                    println("insnNode ${insnNode.desc}")
                    println("insnNode ${insnNode.owner}")
                    println("insnNode ${insnNode.opcode}")
                }

            }
        }
        super.visitEnd()
        accept(classVisitor)
    }
}