package org.wirez.core.definition.adapter.shared;

import org.wirez.core.definition.adapter.DefinitionSetAdapter;
import org.wirez.core.definition.impl.DefinitionSetImpl;
import org.wirez.core.factory.graph.ElementFactory;
import org.wirez.core.graph.Graph;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

// TODO
@ApplicationScoped
public class DefaultDefinitionSetAdapter implements DefinitionSetAdapter<DefinitionSetImpl> {

    @Override
    public boolean accepts(final Class<?> pojo) {
        return pojo.getName().equals(DefinitionSetImpl.class.getName());
    }

    @Override
    public boolean isPojoModel() {
        return false;
    }

    @Override
    public String getId(final DefinitionSetImpl pojo) {
        return null;
    }

    @Override
    public String getDomain(final DefinitionSetImpl pojo) {
        return null;
    }

    @Override
    public String getDescription(final DefinitionSetImpl pojo) {
        return null;
    }

    @Override
    public Set<String> getDefinitions(final DefinitionSetImpl pojo) {
        return null;
    }

    @Override
    public Class<? extends ElementFactory> getGraphFactoryType( DefinitionSetImpl pojo ) {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }
    
}
