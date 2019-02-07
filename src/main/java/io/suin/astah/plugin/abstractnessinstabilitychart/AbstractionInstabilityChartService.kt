package io.suin.astah.plugin.abstractnessinstabilitychart

import com.change_vision.jude.api.inf.editor.ClassDiagramEditor
import com.change_vision.jude.api.inf.editor.ITransactionManager
import com.change_vision.jude.api.inf.exception.BadTransactionException
import com.change_vision.jude.api.inf.model.IClassDiagram
import com.change_vision.jude.api.inf.model.IModel
import io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension.abstractness
import io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension.allPackages
import io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension.hasClasses
import io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension.instability

class AbstractionInstabilityChartService(
    private val project: IModel,
    private val transactionManager: ITransactionManager,
    private val diagramEditor: ClassDiagramEditor
) {
    private val chartName = "Abstractness Instability Chart"

    @Throws(BadTransactionException::class, Exception::class)
    fun createChart(): IClassDiagram = try {
        transactionManager.beginTransaction()
        val classDiagramForChart = createClassDiagramForChart()
        diagramEditor.diagram = classDiagramForChart
        val canvas =
            Canvas(
                diagramEditor,
                1280.0,
                720.0
            )
        canvas.drawChartTitle(chartName)
        canvas.drawMainSequenceLine()
        canvas.drawGrid()
        canvas.drawAxisTitles()
        canvas.drawAxisLabels()

        project
            .allPackages
            .filter { it.hasClasses }
            .forEach {
                canvas.drawPackage(it, it.abstractness, it.instability)
            }
        transactionManager.endTransaction()
        classDiagramForChart
    } catch (e: BadTransactionException) {
        transactionManager.abortTransaction()
        throw e
    } catch (e: Exception) {
        transactionManager.abortTransaction()
        throw e
    }

    private fun createClassDiagramForChart(): IClassDiagram {
        val previousDiagram = project.diagrams.find { it.name == chartName }
        if (previousDiagram is IClassDiagram) {
            diagramEditor.delete(previousDiagram)
        }
        return diagramEditor.createClassDiagram(project, chartName)
    }
}