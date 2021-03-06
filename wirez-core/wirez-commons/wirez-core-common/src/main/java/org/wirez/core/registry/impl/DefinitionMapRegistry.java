package org.wirez.core.registry.impl;

import org.wirez.core.definition.adapter.AdapterManager;
import org.wirez.core.definition.adapter.binding.BindableAdapterUtils;
import org.wirez.core.registry.definition.TypeDefinitionRegistry;

import java.util.HashMap;

class DefinitionMapRegistry<T> extends AbstractDynamicRegistryWrapper<T, MapRegistry<T>> implements TypeDefinitionRegistry<T> {

    private AdapterManager adapterManager;

    DefinitionMapRegistry( final AdapterManager adapterManager ) {
        super(
                new MapRegistry<T>(
                    item -> null != item ? adapterManager.forDefinition().getId( item ) : null,
                    new HashMap<String, T>() )
            );
        this.adapterManager = adapterManager;
    }

    @Override
    public T getDefinitionById( final String id ) {
        return getWrapped().getItemByKey( id );
    }

    @Override
    public T getDefinitionByType( final Class<T> type ) {

        final String id = BindableAdapterUtils.getDefinitionId( type, adapterManager.registry() );

        return getDefinitionById( id );

    }


}
