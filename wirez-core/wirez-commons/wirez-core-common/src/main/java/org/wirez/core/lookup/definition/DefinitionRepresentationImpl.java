package org.wirez.core.lookup.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;

import java.util.Set;

@Portable
public final class DefinitionRepresentationImpl implements DefinitionRepresentation {
    
    private final String id;
    private final boolean isNode;
    private final Set<String> labels;

    public DefinitionRepresentationImpl(@MapsTo("id") String id,
                                        @MapsTo("isNode") boolean isNode,
                                        @MapsTo("labels") Set<String> labels) {
        this.id = id;
        this.isNode = isNode;
        this.labels = labels;
    }

    @Override
    public String getDefinitionId() {
        return id;
    }

    @Override
    public boolean isNode() {
        return isNode;
    }

    @Override
    public Set<String> getLabels() {
        return labels;
    }
}
