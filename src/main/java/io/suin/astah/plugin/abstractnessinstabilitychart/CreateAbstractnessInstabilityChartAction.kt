package io.suin.astah.plugin.abstractnessinstabilitychart

import com.change_vision.jude.api.inf.AstahAPI
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate
import com.change_vision.jude.api.inf.ui.IWindow
import javax.swing.JOptionPane

class CreateAbstractnessInstabilityChartAction : IPluginActionDelegate {
    override fun run(window: IWindow) = try {
        val projectAccessor = AstahAPI.getAstahAPI().projectAccessor
        val service =
            AbstractionInstabilityChartService(
                projectAccessor.project,
                projectAccessor.transactionManager,
                projectAccessor.diagramEditorFactory.classDiagramEditor
            )
        val classDiagram = service.createChart()
        projectAccessor.viewManager.diagramViewManager.open(classDiagram)
        projectAccessor.viewManager.diagramViewManager.zoom(0.5, true)
    } catch (e: ProjectNotFoundException) {
        JOptionPane.showMessageDialog(
            window.parent,
            "Project is not opened.Please open the project or create new project.",
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
}
