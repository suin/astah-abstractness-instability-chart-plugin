package io.suin.astah.jsonexport

import com.change_vision.jude.api.inf.model.*

import com.change_vision.jude.api.inf.presentation.IPresentation
data class Package(val packageName: String, val elements: List<INamedElement>): IPackage {
    constructor(packageName: String, vararg elements: INamedElement): this(packageName, elements.toList())
    override fun getOwnedElements(): Array<INamedElement> = this.elements.toTypedArray()

    override fun isPackageVisibility(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlias1(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTaggedValues(): Array<ITaggedValue> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAlias1(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getId(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComments(): Array<IComment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addStereotype(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createFileHyperlink(
        p0: String?,
        p1: String?,
        p2: String?
    ): IHyperlink {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainers(): Array<IElement> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasStereotype(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTaggedValue(p0: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFullName(p0: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPublicVisibility(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDefinition(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHyperlinks(): Array<IHyperlink> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupplierDependencies(): Array<IDependency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupplierRealizations(): Array<IRealization> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTypeModifier(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStereotypes(): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTypeModifier(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupplierUsages(): Array<IUsage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isProtectedVisibility(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientDependencies(): Array<IDependency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOwner(): IElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPresentations(): Array<IPresentation> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlias2(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDefinition(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientUsages(): Array<IUsage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAlias2(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteHyperlink(p0: IHyperlink?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientRealizations(): Array<IRealization> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConstraints(): Array<IConstraint> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createURLHyperlink(p0: String?, p1: String?): IHyperlink {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPrivateVisibility(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isReadOnly(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDiagrams(): Array<IDiagram> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFullNamespace(p0: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setName(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeStereotype(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainer(): IElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createElementHyperlink(
        p0: IElement?,
        p1: String?
    ): IHyperlink {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setVisibility(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
