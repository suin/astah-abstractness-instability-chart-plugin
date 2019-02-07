package io.suin.astah.jsonexport


import com.change_vision.jude.api.inf.AstahAPI
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException
import com.change_vision.jude.api.inf.project.ProjectAccessor
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate
import com.change_vision.jude.api.inf.ui.IWindow
import io.suin.astah.jsonexport.astah.extension.json.classes
import java.io.File
import javax.swing.JFileChooser
import javax.swing.JOptionPane

class TemplateAction : IPluginActionDelegate {
    @Throws(IPluginActionDelegate.UnExpectedException::class)
    override fun run(window: IWindow): Any? {
        try {
            val api = AstahAPI.getAstahAPI()
            val projectAccessor = api.projectAccessor
            projectAccessor.project
            val classes = projectAccessor.project.classes()
            println(JacksonJsonEncoder().encode(classes))
            1
//            val dir = getOutputDirPath(window, projectAccessor)
//            JOptionPane.showMessageDialog(window.parent, "$dir")
        } catch (e: ProjectNotFoundException) {
            val message =
                    "Project is not opened.Please open the project or create new project."
            JOptionPane.showMessageDialog(
                window.parent,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE
            )
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(
                window.parent,
                "Unexpected error has occurred.",
                "Alert",
                JOptionPane.ERROR_MESSAGE
            )
            throw IPluginActionDelegate.UnExpectedException()
        }

        return null
    }

    @Throws(ProjectNotFoundException::class)
    private fun getOutputDirPath(
        window: IWindow,
        projectAccessor: ProjectAccessor
    ): String? {
        val fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        fileChooser.dialogTitle = "Choose directory"

        val projectDir = File(projectAccessor.projectPath).parentFile
        if (projectDir != null && projectDir.exists() && projectDir.canWrite()) {
            fileChooser.selectedFile = projectDir
        }

        val selected = fileChooser.showSaveDialog(window.parent)
        return if (selected != JFileChooser.CANCEL_OPTION) {
            fileChooser.selectedFile.absolutePath
        } else {
            null //canceled
        }
    }
}
