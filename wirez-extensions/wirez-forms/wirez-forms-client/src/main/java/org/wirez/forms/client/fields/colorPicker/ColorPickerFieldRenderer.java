package org.wirez.forms.client.fields.colorPicker;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.kie.workbench.common.forms.dynamic.client.rendering.FieldRenderer;
import org.wirez.forms.model.ColorPickerFieldDefinition;

@Dependent
public class ColorPickerFieldRenderer extends FieldRenderer<ColorPickerFieldDefinition> {

    private ColorPickerWidget colorPicker;

    @Inject
    public ColorPickerFieldRenderer( ColorPickerWidget colorPicker ) {
        this.colorPicker = colorPicker;
    }

    @Override
    public String getName() {
        return ColorPickerFieldDefinition.CODE;
    }

    @Override
    public void initInputWidget() {

    }

    @Override
    public IsWidget getInputWidget() {
        return colorPicker;
    }

    @Override
    public String getSupportedCode() {
        return ColorPickerFieldDefinition.CODE;
    }
}
