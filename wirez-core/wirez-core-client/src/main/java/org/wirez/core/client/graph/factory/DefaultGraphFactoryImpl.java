package org.wirez.core.client.graph.factory;

import org.wirez.core.api.definition.DefaultDefinition;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.content.ViewContent;
import org.wirez.core.api.graph.content.ViewContentImpl;
import org.wirez.core.api.graph.factory.BaseElementFactory;
import org.wirez.core.api.graph.factory.DefaultGraphFactory;
import org.wirez.core.api.graph.impl.DefaultGraph;
import org.wirez.core.api.graph.impl.DefaultGraphImpl;
import org.wirez.core.api.graph.store.DefaultGraphEdgeStore;
import org.wirez.core.api.graph.store.DefaultGraphNodeStore;
import org.wirez.core.api.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class DefaultGraphFactoryImpl extends BaseElementFactory<DefaultDefinition, DefaultGraph<ViewContent<DefaultDefinition>, Node, Edge>> 
        implements DefaultGraphFactory<DefaultDefinition> {

    @Override
    public DefaultGraph<ViewContent<DefaultDefinition>, Node, Edge> build(DefaultDefinition definition, Set<Property> properties, Set<String> labels) {
        DefaultGraph<ViewContent<DefaultDefinition>, Node, Edge> graph =
                new DefaultGraphImpl<ViewContent<DefaultDefinition>>( UUID.uuid(),
                        properties,
                        labels,
                        new ViewContentImpl<DefaultDefinition>( definition, buildBounds()),
                        new DefaultGraphNodeStore(),
                        new DefaultGraphEdgeStore());

        return graph;
        
    }
}