package io.suin.astah.plugin.abstractnessinstabilitychart

import com.change_vision.jude.api.inf.editor.ClassDiagramEditor
import com.change_vision.jude.api.inf.model.IPackage
import com.change_vision.jude.api.inf.presentation.PresentationPropertyConstants
import java.awt.geom.Point2D

internal class Canvas(
    private val diagramEditor: ClassDiagramEditor,
    private val canvasWidth: Double,
    private val canvasHeight: Double
) {
    private val gridLineStyle = mapOf(
        PresentationPropertyConstants.Key.LINE_COLOR to "#888888",
        PresentationPropertyConstants.Key.LINE_WIDTH to "1",
        PresentationPropertyConstants.Key.LINE_TYPE to PresentationPropertyConstants.Value.LINE_TYPE_DASH1
    )

    fun drawChartTitle(title: String) {
        diagramEditor.createText(
            title,
            Point2D.Double((canvasWidth / 2.0) - 50.0, -10.0)
        )
    }

    fun drawMainSequenceLine() {
        diagramEditor.createLine(
            Point2D.Double(0.0, 0.0),
            Point2D.Double(canvasWidth, canvasHeight)
        ).setProperties(
            mapOf(
                PresentationPropertyConstants.Key.LINE_COLOR to "#00ff00",
                PresentationPropertyConstants.Key.LINE_WIDTH to "2"
            )
        )
    }

    fun drawGrid() {
        for (i in 1..9) {
            val x = (canvasWidth / 10.0) * i.toDouble()
            val y = (canvasHeight / 10.0) * i.toDouble()
            diagramEditor.createLine(
                Point2D.Double(x, 0.0),
                Point2D.Double(x, canvasHeight)
            ).setProperties(gridLineStyle)
            diagramEditor.createLine(
                Point2D.Double(0.0, y),
                Point2D.Double(canvasWidth, y)
            ).setProperties(gridLineStyle)
        }
    }

    fun drawAxisTitles() {
        diagramEditor.createText(
            "Abstractness",
            Point2D.Double(-130.0, (canvasHeight / 2.0) - 10.0)
        )
        diagramEditor.createText(
            "Instability",
            Point2D.Double(
                (canvasWidth / 2.0) - 30.0,
                canvasHeight + 30.0
            )
        )
    }

    fun drawAxisLabels() {
        for (i in 0..10) {
            val x = (canvasWidth / 10.0) * i.toDouble()
            val y = (canvasHeight / 10.0) * i.toDouble()
            diagramEditor.createText(
                String.format("%1.1f", (10.0 - i) / 10.0),
                Point2D.Double(-45.0, y - 10.0)
            )
            diagramEditor.createText(
                String.format("%1.1f", i / 10.0),
                Point2D.Double(x - 20.0, canvasHeight)
            )
        }
    }

    fun drawPackage(pkg: IPackage, abstractness: Double, instability: Double) {
        val text = diagramEditor.createText(
            String.format(
                "%s\n(I%1.2f A%1.2f)",
                pkg.name,
                instability,
                abstractness
            ),
            Point2D.Double(
                canvasWidth * instability,
                canvasHeight * (1.0 - abstractness)
            )
        )
        text.setProperties(
            mapOf(
                PresentationPropertyConstants.Key.FILL_COLOR to "#ffcb00",
                PresentationPropertyConstants.Key.LINE_WIDTH to "1",
                PresentationPropertyConstants.Key.LINE_COLOR to "#000000"
            )
        )
        text.createElementHyperlink(pkg, pkg.getFullName("."))
    }
}