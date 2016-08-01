package org.wirez.basicset.definition.property.font;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.wirez.core.definition.annotation.Name;
import org.wirez.core.definition.annotation.propertyset.Property;
import org.wirez.core.definition.annotation.propertyset.PropertySet;

import javax.validation.Valid;

@Portable
@Bindable
@PropertySet
public class FontSet {

    @Name
    public static final transient String propertySetName = "Font";

    @Property
    @FieldDef(label = "Font Family", property = "value")
    @Valid
    private FontFamily fontFamily;

    @Property
    @FieldDef(label = "Font Color", property = "value")
    @Valid
    private FontColor fontColor;

    @Property
    @FieldDef(label = "Font Size", property = "value")
    @Valid
    private FontSize fontSize;

    @Property
    @FieldDef(label = "Font Border Size", property = "value")
    @Valid
    private FontBorderSize fontBorderSize;

    public FontSet() {
        this(new FontFamily(),
                new FontColor(),
                new FontSize(),
                new FontBorderSize());
    }

    public FontSet(@MapsTo("fontFamily") FontFamily fontFamily,
                   @MapsTo("fontColor") FontColor fontColor,
                   @MapsTo("fontSize") FontSize fontSize,
                   @MapsTo("fontBorderSize") FontBorderSize fontBorderSize) {
        this.fontFamily = fontFamily;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.fontBorderSize = fontBorderSize;
    }

    public FontSet(final String fontFamily,
                   final String fontColor,
                   final Double fontSize,
                   final Double fontBorderSize) {
        this.fontFamily = new FontFamily(fontFamily);
        this.fontColor = new FontColor(fontColor);
        this.fontSize = new FontSize(fontSize);
        this.fontBorderSize = new FontBorderSize(fontBorderSize);
    }

    public String getPropertySetName() {
        return propertySetName;
    }

    public FontFamily getFontFamily() {
        return fontFamily;
    }

    public FontColor getFontColor() {
        return fontColor;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public FontBorderSize getFontBorderSize() {
        return fontBorderSize;
    }

    public void setFontFamily( FontFamily fontFamily ) {
        this.fontFamily = fontFamily;
    }

    public void setFontColor( FontColor fontColor ) {
        this.fontColor = fontColor;
    }

    public void setFontSize( FontSize fontSize ) {
        this.fontSize = fontSize;
    }

    public void setFontBorderSize( FontBorderSize fontBorderSize ) {
        this.fontBorderSize = fontBorderSize;
    }
}
