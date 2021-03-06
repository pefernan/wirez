package org.wirez.bpmn.definition.property.simulation;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.wirez.bpmn.definition.BPMNPropertySet;
import org.wirez.core.definition.annotation.Name;
import org.wirez.core.definition.annotation.propertyset.Property;
import org.wirez.core.definition.annotation.propertyset.PropertySet;

@Portable
@Bindable
@PropertySet
public class ThrowEventAttributes implements BPMNPropertySet {

    @Name
    public static final transient String propertySetName = "Throw Event Attributes";

    @Property
    private Min min;

    @Property
    private Max max;

    @Property
    private Mean mean;

    @Property
    private TimeUnit timeUnit;

    @Property
    private StandardDeviation standardDeviation;

    @Property
    private DistributionType distributionType;

    public ThrowEventAttributes() {
        this( new Min(),
                new Max(),
                new Mean(),
                new TimeUnit(),
                new StandardDeviation(),
                new DistributionType() );
    }

    public ThrowEventAttributes(@MapsTo("min") Min min,
                                @MapsTo("max") Max max,
                                @MapsTo("mean") Mean mean,
                                @MapsTo("timeUnit") TimeUnit timeUnit,
                                @MapsTo("standardDeviation") StandardDeviation standardDeviation,
                                @MapsTo("distributionType") DistributionType distributionType) {
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.timeUnit = timeUnit;
        this.standardDeviation = standardDeviation;
        this.distributionType = distributionType;
    }
    
    public ThrowEventAttributes(Double min,
                                Double max,
                                Double mean,
                                String timeUnit,
                                Double standardDeviation,
                                String distributionType) {
        this.min = new Min( min );
        this.max = new Max( max );
        this.mean = new Mean( mean );
        this.timeUnit = new TimeUnit( timeUnit );
        this.standardDeviation = new StandardDeviation( standardDeviation );
        this.distributionType = new DistributionType( distributionType );
    }

    public String getPropertySetName() {
        return propertySetName;
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
}
