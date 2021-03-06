package org.wirez.core.rule.impl.graph;

import org.wirez.core.api.DefinitionManager;
import org.wirez.core.graph.Element;
import org.wirez.core.graph.content.definition.Definition;
import org.wirez.core.graph.content.definition.DefinitionSet;
import org.wirez.core.graph.content.view.View;
import org.wirez.core.rule.Rule;
import org.wirez.core.rule.RuleManager;
import org.wirez.core.rule.impl.AbstractWrappedRuleManager;

import java.util.Set;

public abstract class AbstractGraphRuleManager<R extends Rule, M extends RuleManager<R>> 
        extends AbstractWrappedRuleManager<R, M> {
    
    protected DefinitionManager definitionManager;

    public AbstractGraphRuleManager( final DefinitionManager definitionManager ) {
        this.definitionManager = definitionManager;
    }

    @SuppressWarnings("unchecked")
    protected String getElementDefinitionId( final Element<?> element ) {
        String targetId = null;
        if ( element.getContent() instanceof View) {
            Object definition = ((View) element.getContent()).getDefinition();
            targetId = getDefinitionId( definition );
        } else if ( element.getContent() instanceof DefinitionSet) {
            targetId = ((DefinitionSet) element.getContent()).getDefinition();
        } 
        return targetId;
    }
    
    @SuppressWarnings("unchecked")
    protected String getDefinitionId( final Object definition ) {
        return definitionManager.adapters().forDefinition().getId( definition );
    }
    
    protected Set<String> getLabels( final Element<? extends Definition<?>> element ) {
        return element != null ? element.getLabels() : null;
    } 
    
}
