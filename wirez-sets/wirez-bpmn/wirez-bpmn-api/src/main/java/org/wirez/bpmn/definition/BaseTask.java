/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.bpmn.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.kie.workbench.common.forms.metaModel.Slider;
import org.wirez.bpmn.definition.property.Height;
import org.wirez.bpmn.definition.property.Width;
import org.wirez.bpmn.definition.property.background.BackgroundSet;
import org.wirez.bpmn.definition.property.dataio.DataIOSet;
import org.wirez.bpmn.definition.property.font.FontSet;
import org.wirez.bpmn.definition.property.general.BPMNGeneral;
import org.wirez.bpmn.definition.property.simulation.*;
import org.wirez.bpmn.definition.property.task.TaskType;
import org.wirez.bpmn.definition.property.task.TaskTypes;
import org.wirez.bpmn.shape.proxy.TaskShapeProxy;
import org.wirez.core.definition.annotation.Description;
import org.wirez.core.definition.annotation.Shape;
import org.wirez.core.definition.annotation.definition.Category;
import org.wirez.core.definition.annotation.definition.Labels;
import org.wirez.core.definition.annotation.definition.Property;
import org.wirez.core.definition.annotation.definition.PropertySet;
import org.wirez.core.definition.annotation.morph.MorphBase;
import org.wirez.core.definition.annotation.morph.MorphProperty;
import org.wirez.core.definition.annotation.morph.MorphPropertyValueBinding;
import org.wirez.core.definition.builder.Builder;
import org.wirez.shapes.factory.BasicShapesFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;

@Shape( factory = BasicShapesFactory.class, proxy = TaskShapeProxy.class )
@MorphBase( defaultType = NoneTask.class, targets = { ReusableSubprocess.class } )
public abstract class BaseTask implements BPMNDefinition {

    @Category
    public static final transient String category = Categories.ACTIVITIES;

    @Description
    public static final transient String description = "A task is a unit of work - the job to be performed";

    @PropertySet
    @FieldDef( label = "General Settings", position = 0)
    @Valid
    protected BPMNGeneral general;

    @PropertySet
    @FieldDef( label = "Task Data", position = 1)
    @Valid
    protected DataIOSet dataIOSet;

    @PropertySet
    @FieldDef( label = "Background Settings", position = 2)
    @Valid
    protected BackgroundSet backgroundSet;

    @PropertySet
    protected FontSet fontSet;

    @Property
    @FieldDef(label = "Width", property = "value")
    @Slider( min = 100.0, max = 300.0, step = 10.0, precision = 0.0 )
    @Valid
    protected Width width;

    @Property
    @FieldDef(label = "Height", property = "value")
    @Slider( min = 40.0, max = 100.0, step = 5.0, precision = 0.0 )
    @Valid
    protected Height height;

    @Property
    protected Min min;

    @Property
    protected Max max;

    @Property
    protected Mean mean;

    @Property
    @FieldDef(label = "TimeUnit", property = "value")
    @Valid
    protected TimeUnit timeUnit;

    @Property
    @FieldDef(label = "Standard Deviation", property = "value")
    protected StandardDeviation standardDeviation;

    @Property
    @FieldDef(label = "Distribution Type", property = "value")
    protected DistributionType distributionType;

    @Property
    @FieldDef(label = "Quantity", property = "value")
    protected Quantity quantity;

    @Property
    @FieldDef(label = "WorkingHours", property = "value")
    protected WorkingHours workingHours;

    @Property
    @FieldDef(label = "UnitCost", property = "value")
    protected UnitCost unitCost;

    @Property
    @FieldDef(label = "Currency", property = "value")
    protected Currency currency;

    @Property
    @FieldDef(label = "Task Type", property = "value")
    @MorphProperty( binder = TaskTypeMorphPropertyBinding.class )
    protected TaskType taskType;

    public static class TaskTypeMorphPropertyBinding implements MorphPropertyValueBinding<TaskType, TaskTypes> {

        private static final Map<TaskTypes, Class<?>> MORPH_TARGETS =
                new HashMap<TaskTypes, Class<?>>( 4 ) {{
                    put( TaskTypes.NONE, NoneTask.class );
                    put( TaskTypes.USER, UserTask.class );
                    put( TaskTypes.SCRIPT, ScriptTask.class );
                    put( TaskTypes.BUSINESS_RULE, BusinessRuleTask.class );
                }};

        @Override
        public TaskTypes getValue( final TaskType property ) {
            return property.getValue();
        }

        @Override
        public Map<TaskTypes, Class<?>> getMorphTargets() {
            return MORPH_TARGETS;
        }

    }

    @Labels
    protected final Set<String> labels = new HashSet<String>() {{
        add( "all" );
        add( "sequence_start" );
        add( "sequence_end" );
        add( "from_task_event" );
        add( "to_task_event" );
        add( "FromEventbasedGateway" );
        add( "messageflow_start" );
        add( "messageflow_end" );
        add( "fromtoall" );
        add( "ActivitiesMorph" );
    }};

    @NonPortable
    static abstract class BaseTaskBuilder<T extends BaseTask> implements Builder<T> {

        public static final String COLOR = "#f9fad2";
        public static final Double WIDTH = 136d;
        public static final Double HEIGHT = 48d;
        public static final Double BORDER_SIZE = 1d;
        public static final String BORDER_COLOR = "#000000";

    }

    protected BaseTask( final TaskTypes type ) {
        this.taskType = new TaskType( type );
    }

    public BaseTask(@MapsTo("general") BPMNGeneral general,
                    @MapsTo("dataIOSet") DataIOSet dataIOSet,
                    @MapsTo("backgroundSet") BackgroundSet backgroundSet,
                    @MapsTo("fontSet") FontSet fontSet,
                    @MapsTo("width") Width width,
                    @MapsTo("height") Height height,
                    @MapsTo("min") Min min,
                    @MapsTo("max") Max max,
                    @MapsTo("mean") Mean mean,
                    @MapsTo("timeUnit") TimeUnit timeUnit,
                    @MapsTo("standardDeviation") StandardDeviation standardDeviation,
                    @MapsTo("distributionType") DistributionType distributionType,
                    @MapsTo("quantity") Quantity quantity,
                    @MapsTo("workingHours") WorkingHours workingHours,
                    @MapsTo("unitCost") UnitCost unitCost,
                    @MapsTo("currency") Currency currency,
                    @MapsTo("taskType") TaskType taskType ) {
        this.general = general;
        this.dataIOSet = dataIOSet;
        this.backgroundSet = backgroundSet;
        this.fontSet = fontSet;
        this.width = width;
        this.height = height;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.timeUnit = timeUnit;
        this.standardDeviation = standardDeviation;
        this.distributionType = distributionType;
        this.quantity = quantity;
        this.workingHours = workingHours;
        this.unitCost = unitCost;
        this.currency = currency;
        this.taskType = taskType;

    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public BPMNGeneral getGeneral() {
        return general;
    }

    public DataIOSet getDataIOSet() {
        return dataIOSet;
    }

    public BackgroundSet getBackgroundSet() {
        return backgroundSet;
    }

    public FontSet getFontSet() {
        return fontSet;
    }

    public Width getWidth() {
        return width;
    }

    public Height getHeight() {
        return height;
    }

    public Min getMin() {
        return min;
    }

    public Max getMax() {
        return max;
    }

    public Mean getMean() {
        return mean;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public StandardDeviation getStandardDeviation() {
        return standardDeviation;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public UnitCost getUnitCost() {
        return unitCost;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setGeneral( BPMNGeneral general ) {
        this.general = general;
    }

    public void setDataIOSet( DataIOSet dataIOSet ) {
        this.dataIOSet = dataIOSet;
    }

    public void setBackgroundSet( BackgroundSet backgroundSet ) {
        this.backgroundSet = backgroundSet;
    }

    public void setFontSet( FontSet fontSet ) {
        this.fontSet = fontSet;
    }

    public void setWidth( Width width ) {
        this.width = width;
    }

    public void setHeight( Height height ) {
        this.height = height;
    }

    public void setMin( Min min ) {
        this.min = min;
    }

    public void setMax( Max max ) {
        this.max = max;
    }

    public void setMean( Mean mean ) {
        this.mean = mean;
    }

    public void setTimeUnit( TimeUnit timeUnit ) {
        this.timeUnit = timeUnit;
    }

    public void setStandardDeviation( StandardDeviation standardDeviation ) {
        this.standardDeviation = standardDeviation;
    }

    public void setDistributionType( DistributionType distributionType ) {
        this.distributionType = distributionType;
    }

    public void setQuantity( Quantity quantity ) {
        this.quantity = quantity;
    }

    public void setWorkingHours( WorkingHours workingHours ) {
        this.workingHours = workingHours;
    }

    public void setUnitCost( UnitCost unitCost ) {
        this.unitCost = unitCost;
    }

    public void setCurrency( Currency currency ) {
        this.currency = currency;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType( TaskType taskType ) {
        this.taskType = taskType;
    }
}
