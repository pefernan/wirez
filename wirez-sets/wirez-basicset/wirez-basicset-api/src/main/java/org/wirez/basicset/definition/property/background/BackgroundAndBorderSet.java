package org.wirez.basicset.definition.property.background;

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
public class BackgroundAndBorderSet {

    @Name
    public static final transient String propertySetName = "Background And Borders";

    @Property
    @FieldDef(label = "Background Color", property = "value")
    @Valid
    private BgColor bgColor;

    @Property
    @FieldDef(label = "Border Color", property = "value")
    @Valid
    private BorderColor borderColor;

    @Property
    @FieldDef(label = "Border Size", property = "value")
    @Valid
    private BorderSize borderSize;

    public BackgroundAndBorderSet() {
        this( new BgColor(),
                new BorderColor(),
                new BorderSize() );
    }

    public BackgroundAndBorderSet(@MapsTo("bgColor") BgColor bgColor,
                                  @MapsTo("borderColor") BorderColor borderColor,
                                  @MapsTo("borderSize") BorderSize borderSize) {
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderSize = borderSize;
    }

    public BackgroundAndBorderSet(final String bgColor,
                                  final String borderColor,
                                  final Double borderSize) {
        this.bgColor = new BgColor( bgColor );
        this.borderColor = new BorderColor( borderColor );
        this.borderSize = new BorderSize( borderSize );
    }

    public String getPropertySetName() {
        return propertySetName;
    }

    public BgColor getBgColor() {
        return bgColor;
    }

    public BorderColor getBorderColor() {
        return borderColor;
    }

    public BorderSize getBorderSize() {
        return borderSize;
    }

    public void setBgColor( BgColor bgColor ) {
        this.bgColor = bgColor;
    }

    public void setBorderColor( BorderColor borderColor ) {
        this.borderColor = borderColor;
    }

    public void setBorderSize( BorderSize borderSize ) {
        this.borderSize = borderSize;
    }
}
