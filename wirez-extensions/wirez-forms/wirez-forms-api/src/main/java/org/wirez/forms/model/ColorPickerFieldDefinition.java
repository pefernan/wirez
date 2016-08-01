package org.wirez.forms.model;

import javax.validation.constraints.Pattern;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.kie.workbench.common.forms.model.FieldDefinition;
import org.wirez.forms.meta.definition.ColorPicker;

@Portable
@Bindable
public class ColorPickerFieldDefinition extends FieldDefinition {

    public static final String CODE = "ColorPicker";


    @FieldDef( label = "Default value")
    @ColorPicker
    @Pattern( regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Invalid color code")
    private String defaultValue;

    public ColorPickerFieldDefinition() {
        super( CODE );
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue( String defaultValue ) {
        this.defaultValue = defaultValue;
    }

    @Override
    protected void doCopyFrom( FieldDefinition other ) {
        if ( other instanceof  ColorPickerFieldDefinition ) {
            this.setDefaultValue( ( (ColorPickerFieldDefinition) other ).getDefaultValue() );
        }
    }
}
