/*
 * Copyright 2016  Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.bpmn.definition.property.variables;

import javax.validation.Valid;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.wirez.bpmn.definition.BPMNPropertySet;
import org.wirez.core.definition.annotation.Name;
import org.wirez.core.definition.annotation.propertyset.Property;
import org.wirez.core.definition.annotation.propertyset.PropertySet;
import org.wirez.forms.meta.definition.VariablesEditor;

@Portable
@Bindable
@PropertySet
public class ProcessData implements BPMNPropertySet {

    @Name
    public static final transient String propertySetName = "Process Data";

    @Property
    @FieldDef(label = "Process Variables", property = "value")
    @VariablesEditor
    @Valid
    private ProcessVariables processVariables;

    @Property
    @FieldDef(label = "Globals", property = "value")
    @VariablesEditor
    @Valid
    private GlobalVariables globalVariables;

    public ProcessData() {
        this(new ProcessVariables(), new GlobalVariables());
    }

    public ProcessData(@MapsTo("processVariables") ProcessVariables processVariables, @MapsTo("globalVariables") GlobalVariables globalVariables) {
        this.processVariables = processVariables;
        this.globalVariables = globalVariables;
    }

    public ProcessData(String processVariables, String globalVariables) {
        this.processVariables = new ProcessVariables(processVariables);
        this.globalVariables = new GlobalVariables(globalVariables);
    }

    public String getPropertySetName() {
        return propertySetName;
    }

    public ProcessVariables getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(ProcessVariables processVariables) {
        this.processVariables = processVariables;
    }

    public GlobalVariables getGlobalVariables() {
        return globalVariables;
    }

    public void setGlobalVariables(GlobalVariables globalVariables) {
        this.globalVariables = globalVariables;
    }

}
