package org.wirez.bpmn.backend.marshall.json.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wirez.bpmn.api.BPMNDefinition;
import org.wirez.core.api.adapter.DefinitionAdapter;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.content.ViewContent;
import org.wirez.core.api.util.ElementUtils;

import java.util.*;

public abstract class AbstractObjectBuilder<W extends Definition, T extends Element<ViewContent<W>>> implements GraphObjectBuilder<W, T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractObjectBuilder.class);

    protected String nodeId;
    protected Map<String, String> properties;
    protected Set<String> outgoingNodeIds;
    protected BPMNGraphObjectBuilderFactory bpmnGraphFactory;
            
    public AbstractObjectBuilder(BPMNGraphObjectBuilderFactory bpmnGraphFactory) {
        this.bpmnGraphFactory = bpmnGraphFactory;
        properties = new HashMap<String, String>();
        outgoingNodeIds = new LinkedHashSet<String>();
    }

    @Override
    public GraphObjectBuilder<W, T> nodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @Override
    public GraphObjectBuilder<W, T> property(String key, String value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public GraphObjectBuilder<W, T> out(String nodeId) {
        outgoingNodeIds.add(nodeId);
        return this;
    }

    @Override
    public GraphObjectBuilder<W, T> stencil(String stencilId) {
        return this;
    }

    protected GraphObjectBuilder<?, ?> getBuilder(BuilderContext context, String nodeId) {
        Collection<GraphObjectBuilder<?, ?>> builders = context.getBuilders();
        if (builders != null && !builders.isEmpty()) {
            for (GraphObjectBuilder<?, ?> builder : builders) {
                AbstractObjectBuilder<?, ?> abstractBuilder = (AbstractObjectBuilder<?, ?>) builder;
                if (abstractBuilder.nodeId.equals(nodeId)) {
                    return builder;
                }
            }
        }
        return null;
    }
    
    protected void setProperties(final BPMNDefinition definition) {
        assert definition != null;
        DefinitionAdapter adapter = bpmnGraphFactory.getDefinitionManager().getDefinitionAdapter(definition);
        Map<Property, Object> defPropertiesMap = adapter.getPropertiesValues(definition);
        Set<Property> defProperties = defPropertiesMap.keySet();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            final String pId = entry.getKey();
            final String pValue = entry.getValue();
            final Property property = ElementUtils.getPropertyIgnoreCase(defProperties, pId);
            if ( null != property ) {
                final Object value;
                try {
                    value = ElementUtils.parseValue(property, pValue);
                    bpmnGraphFactory.getDefinitionManager().getPropertyAdapter(property).setValue(property, value);
                } catch (Exception e) {
                   LOG.error("Cannot parse value [" + pValue + "] for property [" + pId + "]");
                }
            } else {
                LOG.warn("Property [" + pId + "] not found for definition [" + definition.getId() + "]");
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[NodeId=").append(nodeId).append("]");
        builder.append("[properties=").append(properties).append("]");
        builder.append("[outgoingNodeIds=").append(outgoingNodeIds).append("]");
        return builder.toString();
    }
}