package org.wirez.client.lienzo.components.palette.impl;

import org.wirez.client.lienzo.components.palette.AbstractLienzoGlyphItemsPalette;
import org.wirez.client.lienzo.components.palette.LienzoGlyphsPalette;
import org.wirez.client.lienzo.components.palette.view.LienzoPaletteViewImpl;
import org.wirez.core.client.ShapeManager;
import org.wirez.core.client.components.glyph.DefinitionGlyphTooltip;
import org.wirez.core.client.components.palette.model.GlyphPaletteItem;
import org.wirez.core.client.components.palette.model.HasPaletteItems;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LienzoGlyphsPaletteImpl
        extends AbstractLienzoGlyphItemsPalette<HasPaletteItems<? extends GlyphPaletteItem>, LienzoPaletteViewImpl>
        implements LienzoGlyphsPalette {

    protected LienzoGlyphsPaletteImpl() {
        this( null, null, null );
    }

    @Inject
    public LienzoGlyphsPaletteImpl(final ShapeManager shapeManager,
                                   final DefinitionGlyphTooltip definitionGlyphTooltip,
                                   final LienzoPaletteViewImpl view ) {
        super( shapeManager, definitionGlyphTooltip, view );
    }

    @PostConstruct
    public void init() {
        super.doInit();
    }

}
