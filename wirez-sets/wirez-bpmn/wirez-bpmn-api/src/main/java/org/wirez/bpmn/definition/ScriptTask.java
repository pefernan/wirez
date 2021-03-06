package org.wirez.bpmn.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.wirez.bpmn.definition.property.Height;
import org.wirez.bpmn.definition.property.Width;
import org.wirez.bpmn.definition.property.background.BackgroundSet;
import org.wirez.bpmn.definition.property.dataio.DataIOSet;
import org.wirez.bpmn.definition.property.font.FontSet;
import org.wirez.bpmn.definition.property.general.BPMNGeneral;
import org.wirez.bpmn.definition.property.simulation.*;
import org.wirez.bpmn.definition.property.task.Script;
import org.wirez.bpmn.definition.property.task.TaskType;
import org.wirez.bpmn.definition.property.task.TaskTypes;
import org.wirez.core.definition.annotation.definition.Definition;
import org.wirez.core.definition.annotation.definition.Property;
import org.wirez.core.definition.annotation.definition.Title;
import org.wirez.core.definition.annotation.morph.Morph;
import org.wirez.core.factory.graph.NodeFactory;
import org.wirez.core.rule.annotation.CanDock;

@Portable
@Bindable
@Definition( graphFactory = NodeFactory.class, builder = ScriptTask.ScriptTaskBuilder.class )
@CanDock( roles = { "IntermediateEventOnActivityBoundary" } )
@Morph( base = BaseTask.class )
public class ScriptTask extends BaseTask {

    @Title
    public static final transient String title = "Script Task";

    @Property
    protected Script script;

    @NonPortable
    public static class ScriptTaskBuilder extends BaseTaskBuilder<ScriptTask> {

        @Override
        public ScriptTask build() {
            return new ScriptTask(new BPMNGeneral("Task"),
                    new DataIOSet(),
                    new BackgroundSet(COLOR, BORDER_COLOR, BORDER_SIZE),
                    new FontSet(),
                    new Width(WIDTH),
                    new Height(HEIGHT),
                    new Min(),
                    new Max(),
                    new Mean(),
                    new TimeUnit(),
                    new StandardDeviation(),
                    new DistributionType(),
                    new Quantity(),
                    new WorkingHours(),
                    new UnitCost(),
                    new Currency(),
                    new TaskType( TaskTypes.SCRIPT ),
                    new Script());
        }

    }

    public ScriptTask() {
        super( TaskTypes.SCRIPT );
    }

    public ScriptTask(@MapsTo("general") BPMNGeneral general,
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
                      @MapsTo("taskType") TaskType taskType,
                      @MapsTo("script") Script script) {

        super(general, dataIOSet, backgroundSet, fontSet, width, height, min, max, mean, timeUnit, standardDeviation,
                distributionType, quantity, workingHours, unitCost, currency, taskType);
        this.script = script;

    }

    public String getTitle() {
        return title;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
